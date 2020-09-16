package Product;

import Product.Producer;
import Product.Product;
import Product.Promotion;
import Product.Image;
import User.User;
import Dao.Dao;
import Dao.Dao;
import User.Account;
import User.Address;
import User.Customer;
import User.FullName;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class ProductDao extends Dao  implements ProductInterface{

    private static volatile ProductDao instance;
    String sql = "select DISTINCT * from Category,Image,Producer,Rating,Promotion,Product WHERE product.categorieID=category.id and product.imageid=image.id and product.producerid=producer.id and product.ratingid=rating.id and product.promotionid=promotion.id";

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        if (instance == null) {
            instance = new ProductDao();
        }
        return instance;
    }

    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = resultSetToProduct(rs);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    
     public List<Product> getbySearchName(String name) {
         System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+name);
        String sql1= sql+" AND product.nameProduct LIKE ?";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setString(1,"%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = resultSetToProduct(rs);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
     

    public Product getbyID(int id) {
        String sql1 = sql + " And product.idproduct=? ;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return resultSetToProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getbyCategoryID(int id) {
        List<Product> products = new ArrayList<>();
        String sql1 = sql + " And category.id=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = resultSetToProduct(rs);
                if (product != null) {
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }
    

    private Product resultSetToProduct(ResultSet rs) {
        try {
            Product product = new Product();
            product.setId(rs.getInt("idProduct"));
            Categorie categorie = new Categorie();
            categorie.setId(rs.getInt("CategorieID"));
            categorie.setName(rs.getString("nameCategory"));
            categorie.setParent(rs.getString("parent"));

            product.setCategories(categorie);

            Image image = new Image();
            image.setId(rs.getInt("imageid"));
            image.setUrl(rs.getString("url"));
            product.setImage(image);

            Producer producer = new Producer();
            producer.setId(rs.getInt("producerid"));
            producer.setName(rs.getString("nameproducer"));
            product.setProducer(producer);

            Promotion promotion = new Promotion();
            promotion.setId(rs.getInt("promotionid"));
            promotion.setPromotionName(rs.getString("promotionName"));
            promotion.setPricePromotion(rs.getDouble("pricePromotion"));
            product.setPromotion(promotion);

            Rating rating = new Rating();
            rating.setId(rs.getInt("ratingid"));
            rating.setStar(rs.getInt("star"));
            rating.setNumberVote(rs.getInt("numbervote"));

            product.setRating(rating);
            product.setName(rs.getString("nameproduct"));
            product.setPriceOrigin(rs.getDouble("priceorigin"));

            return product;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(Product product) throws SQLException {
        connection.setAutoCommit(false);
        String insertImage = "INSERT INTO `image` (`Url`) VALUES (?);";
        String insertPromotion = "INSERT INTO `promotion` (`PromotionName`, `PricePromotion`) VALUES (?, ?);";
        String insertRating = "INSERT INTO `rating` (`Star`, `NumberVote`) VALUES (?, ?);";
        String insertProduct = "INSERT INTO `product` (`NameProduct`, `PriceOrigin`, `CategorieID`, `ImageID`, `ProducerID`, `RatingID`, `PromotionID`) VALUES ( ?, ?, ?, ?, ?, ?, ?);";

        
        PreparedStatement ps4 = connection.prepareStatement(insertProduct, Statement.RETURN_GENERATED_KEYS);
        ps4.setString(1, product.getName());
        ps4.setDouble(2, product.getPriceOrigin());
     
        
     
        PreparedStatement ps = connection.prepareStatement(insertImage, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, product.getImage().getUrl());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            System.out.println("key1 la " + rs.getInt(1));
            product.getImage().setId(rs.getInt(1));
        } else {
            System.out.println("co loi xay ra1");
            connection.rollback();
            return false;
        }
        
        
                  
            if(product.getRating()==null){
           ps4.setInt(6, 11);
        }else{
        PreparedStatement ps3 = connection.prepareStatement(insertRating, Statement.RETURN_GENERATED_KEYS);
        ps3.setInt(1, product.getRating().getStar());
        ps3.setInt(2, product.getRating().getNumberVote());

        ps3.executeUpdate();
        ResultSet rs3 = ps3.getGeneratedKeys();
        if (rs3.next()) {
            System.out.println("key3 la " + rs3.getInt(1));
            product.getRating().setId(rs3.getInt(1));
            ps4.setInt(6, rs3.getInt(1));
        } else {
            System.out.println("co loi xay ra3");
            connection.rollback();
            return false;
        }
         }
        
        
          if(product.getPromotion()==null){
           ps4.setInt(7, 11);
        }else{
        PreparedStatement ps2 = connection.prepareStatement(insertPromotion, Statement.RETURN_GENERATED_KEYS);
        ps2.setString(1, product.getPromotion().getPromotionName());
        ps2.setDouble(2, product.getPromotion().getPricePromotion());

        ps2.executeUpdate();
        ResultSet rs2 = ps2.getGeneratedKeys();
        if (rs2.next()) {
            System.out.println("key3 la " + rs2.getInt(1));
            product.getPromotion().setId(rs2.getInt(1));
              ps4.setInt(7, rs2.getInt(1));
        } else {
            System.out.println("co loi xay ra2");
            connection.rollback();
            return false;
        }
        }

           //Add to tbl Product
         ps4.setInt(3, product.getCategories().getId());
        ps4.setInt(4, product.getImage().getId());
        ps4.setInt(5, product.getProducer().getId());
        ps4.executeUpdate();
        ResultSet rs4 = ps4.getGeneratedKeys();
        if (rs4.next()) {
            System.out.println("key4 la " + rs4.getInt(1));
            System.out.println("Them san pham thanh cong");
        } else {
            connection.rollback();
            return false;
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    public boolean update(Product product) {
        return true;

    }

    public boolean delete(int id) throws SQLException {
        System.out.println("meomeo" +id);
        String sql="DELETE FROM product WHERE idProduct=?;";
         PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        return true;    
    }


}
