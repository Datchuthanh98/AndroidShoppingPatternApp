package Product;


import Dao.Dao;
import Dao.Dao;
import User.Account;
import User.Address;
import User.Customer;
import User.FullName;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class CategoryDao extends Dao implements CategoryInterface{
     private static volatile CategoryDao instance;
    
       private  String sql = "select DISTINCT * from category";
    
    private CategoryDao(){}
    
      public static CategoryDao getInstance(){
       if (instance == null){
           instance = new CategoryDao();
       }
       return instance;
   }
      
      @Override
   public List<Categorie> getAll(){
      List<Categorie> categories = new ArrayList<>();
      try{
          PreparedStatement ps = connection.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          while (rs.next()){  
              Categorie categorie = resultSetToCustomer(rs);
           if(categorie!=null){
              categories.add(categorie);
           }
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return categories;
   }
   
   
  
      @Override
     public Categorie getCategorybyID(int id){
      String sql1 = sql+" where category.ID=? ;";
      try{
          PreparedStatement ps = connection.prepareStatement(sql1);
          ps.setInt(1, id);
          ResultSet rs = ps.executeQuery();
               while (rs.next()){  
             return  resultSetToCustomer(rs);
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return null;
   }
   
   
   private Categorie resultSetToCustomer(ResultSet rs) {
        try {Categorie categorie = new Categorie();
            categorie.setId(rs.getInt("id"));
            categorie.setName(rs.getString("nameCategory"));
            categorie.setParent(rs.getString("parent"));
            return categorie;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 

}
