package Shipment;

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
public class ShippingDao extends Dao {

    private static volatile ShippingDao instance;
    String dkShip = "shipping.methodShippingid=methodshipping.id And shipping.compaynyshipid=companyship.id";
    String tableship = " methodshipping,companyship,shipping";
    private String sql = "select DISTINCT * from " + tableship + " where " + dkShip;

    private ShippingDao() {
    }

    public static ShippingDao getInstance() {
        if (instance == null) {
            instance = new ShippingDao();
        }
        return instance;
    }

    public List<Shipping> getAll() {
        List<Shipping> shippings = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Shipping shipping = resultSetToCustomer(rs);
                if (shipping != null) {
                    shippings.add(shipping);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shippings;
    }

    public Shipping getShippingbyID(int id) {
        String sql1 = sql + " AND shipping.idshipping=? ;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return resultSetToCustomer(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Shipping shipping) throws SQLException {
        connection.setAutoCommit(false);
        String insertShipment = "INSERT INTO `shipping` (`StartShipping`, `AddressShipping`, `StatusShipping`, `MethodShippingID`, `CompaynyShipID`) VALUES ( ?, ?, ?,?, ?);";
        PreparedStatement ps = connection.prepareStatement(insertShipment, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, shipping.getStartShipping());
        ps.setString(2, shipping.getAddressShipping());
        ps.setString(3, shipping.getShippingStatus());
        ps.setInt(4, shipping.getMethodShipping().getId());
        ps.setInt(5, shipping.getCompanyShip().getId());
        ps.executeUpdate();
         int idShipping=0;
        ResultSet rs = ps.getGeneratedKeys();
          if (rs.next()) {
           idShipping=rs.getInt(1);
            //toto add cartItem
        } else {
            System.out.println("co loi xay ra1");
            connection.rollback();
            return 0;
        }
        connection.commit();
        connection.setAutoCommit(true);
        return idShipping;
    }

      
    public boolean updateStateShipping(Shipping shipping) throws SQLException{
        String updateState="UPDATE `shipping` SET `StatusShipping` =?  WHERE `shipping`.`IDshipping` = ?;"; 
         PreparedStatement ps = connection.prepareStatement(updateState);
         ps.setString(1, shipping.getShippingStatus());
         ps.setInt(2,shipping.getId());
         ps.executeUpdate();
          ResultSet rs = ps.getResultSet();
        return true;
    }
    
    private Shipping resultSetToCustomer(ResultSet rs) {
        try {
            Shipping shipping = new Shipping();
            shipping.setId(rs.getInt("idshipping"));
            shipping.setStartShipping(rs.getString("startShipping"));
            shipping.setAddressShipping(rs.getString("addressShipping"));
            shipping.setShippingStatus(rs.getString("statusShipping"));
            MethodShipping methodShipping = new MethodShipping();
            methodShipping.setNameMethodShip(rs.getString("namemethodshipping"));
            CompanyShip companyShip = new CompanyShip();
            companyShip.setNameCompanyShip(rs.getString("namecompanyship"));
            shipping.setMethodShipping(methodShipping);
            shipping.setCompanyShip(companyShip);
            return shipping;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
