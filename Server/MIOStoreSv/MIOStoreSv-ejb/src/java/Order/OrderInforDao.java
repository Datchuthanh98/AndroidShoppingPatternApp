package Order;

import Shipment.Shipping;
import Payment.Payment;
import Product.ProductDao;
import User.CustomerDao;
import Dao.Dao;
import Dao.Dao;
import Payment.PaymentDao;
import Shipment.ShippingDao;
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
public class OrderInforDao extends Dao implements OrderInterface{

    private static volatile OrderInforDao instance;
    private CustomerDao customerDao = CustomerDao.getInstance();
    private ShippingDao shippingDao = ShippingDao.getInstance();
    private PaymentDao paymentDao = PaymentDao.getInstance();
    private ProductDao productDao = ProductDao.getInstance();

    String sql = "select DISTINCT * from orderinfor ";

    private OrderInforDao() {
    }

    public static OrderInforDao getInstance() {
        if (instance == null) {
            instance = new OrderInforDao();
        }
        return instance;
    }

    public List<OrderInfor> getAll() {
        List<OrderInfor> orders = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderInfor orderinfor = resultSetToOder(rs);
                if (orderinfor != null) {
                    orders.add(orderinfor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrderInfor> getOrderbyCustomerID(int id) {
        List<OrderInfor> orders = new ArrayList<>();
        String sql1 = "select DISTINCT * from orderinfor where CustomerUserid=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderInfor orderinfor = resultSetToOder(rs);
                if (orderinfor != null) {
                    orders.add(orderinfor);
                    System.out.println("get one item");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return orders;
    }

    public OrderInfor getOrderbyOrderID(int id) {

        String sql1 = " select DISTINCT * from orderinfor where IDorder=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return resultSetToOder(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CartItem> getListCartItemByOrderID(int id) {
        List<CartItem> listCartItem = new ArrayList<>();
        String sql = "select DISTINCT * from cartitem where OrdersID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("ID"));
                cartItem.setProduct(productDao.getbyID(rs.getInt("ProductID")));
                cartItem.setQuantity(rs.getInt("Quantity"));
                listCartItem.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listCartItem;
    }

    private OrderInfor resultSetToOder(ResultSet rs) {
        try {
            OrderInfor order = new OrderInfor();
            order.setId(rs.getInt("IDorder"));
            order.setTotal(rs.getDouble("total"));
            order.setCreateDate(rs.getString("CreateDateOrder"));
            order.setStatusOrder(rs.getString("statusorder"));

            int idPay = rs.getInt("paymentid");
            Payment payment = paymentDao.getPaymentbyID(idPay);
            int idShip = rs.getInt("shippingid");
            Shipping shipping = shippingDao.getShippingbyID(idPay);
            int idCus = rs.getInt("customerUserid");
            Customer customer = customerDao.getCustomerbyID(idCus);

            order.setCustomer(customer);
            order.setPayment(payment);
            order.setShipping(shipping);
            
            order.setCartitem(getListCartItemByOrderID(rs.getInt("IDorder")));
            return order;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
  
    

    public boolean insert(OrderInfor orderInfor) throws SQLException {
        connection.setAutoCommit(false);
        String insertOrder = "INSERT INTO `orderinfor` ( `Total`, `CreateDateOrder`, `StatusOrder`, `ShippingID`, `PaymentID`, `CustomerUserID`) VALUES ( ?, ?, ?, ?, ?,?);";
        int idPayment= paymentDao.insert(orderInfor.getPayment());
        int idShipping=  shippingDao.insert(orderInfor.getShipping());
        PreparedStatement ps = connection.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, orderInfor.getTotal());
        ps.setString(2, orderInfor.getCreateDate());
        ps.setString(3, orderInfor.getStatusOrder());
        ps.setInt(4, idShipping);
        ps.setInt(5, idPayment);
        ps.setInt(6, orderInfor.getCustomer().getId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            System.out.println("key1 la " + rs.getInt(1));
            System.out.println("Tao order thanh cong");
            orderInfor.setId(rs.getInt(1));
            //toto add cartItem
        } else {
            System.out.println("co loi xay ra1");
            connection.rollback();
            return false;
        }

        // INSERT INTO CartItem(quantity, orderId, productId) VALUES(?,?,?),(?,?,?),(?,?,?)
        String insertItem = "INSERT INTO CartItem(quantity, ordersId, productId) VALUES(?, ?, ?)";
        if (!orderInfor.getCartitem().isEmpty()) {
            for (int i = 0; i < orderInfor.getCartitem().size() - 1; i++) {
                insertItem += ",(?,?,?)";
            }
            PreparedStatement ps2 = connection.prepareStatement(insertItem);
            int index = 1;
            for (CartItem item : orderInfor.getCartitem()) {
                System.out.println(item.getQuantity());
                ps2.setInt(index++, item.getQuantity());
                ps2.setInt(index++, orderInfor.getId());
                ps2.setInt(index++, item.getProduct().getId());
            }
            if (ps2.executeUpdate() != orderInfor.getCartitem().size()) {
                connection.rollback();
                return false;
            }
        }
         
        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    public boolean update() {
        return true;

    }

    public boolean delete(int id) {
        return false;
    }

}
