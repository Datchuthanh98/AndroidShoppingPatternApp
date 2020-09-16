/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public interface CustomerInterface {
   List<Customer> getAll();
    Customer getCustomerbyID(int id) throws SQLException;
    boolean insert(Customer customer) throws SQLException;
    boolean update(Customer customer) throws SQLException;
     boolean delete(int id) throws SQLException;
     Customer login(Customer customer) throws SQLException;
}
