/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product;

import Product.Product;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ProductInterface {
     List<Product> getAll();
     Product getbyID(int id);
     List<Product> getbyCategoryID(int id);
     boolean insert(Product product) throws SQLException;
     boolean update(Product product) throws SQLException;
     boolean delete(int id ) throws SQLException;
     public List<Product> getbySearchName(String name) throws SQLException;
    
    
     
}
