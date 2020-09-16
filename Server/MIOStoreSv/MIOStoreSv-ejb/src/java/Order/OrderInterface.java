/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import Order.CartItem;
import Order.OrderInfor;
import User.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public interface OrderInterface {
    List<OrderInfor> getAll() ;
    List<OrderInfor> getOrderbyCustomerID(int id);
    OrderInfor getOrderbyOrderID(int id);
    List<CartItem> getListCartItemByOrderID(int id);
     boolean insert(OrderInfor orderInfor) throws SQLException;
     boolean update();
     boolean delete(int id);
}
