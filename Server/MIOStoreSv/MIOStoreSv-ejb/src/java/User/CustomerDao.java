package User;

import Dao.Dao;
import Dao.Dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CustomerDao extends Dao implements CustomerInterface{

    private static volatile CustomerDao instance;
    private String sql = "select DISTINCT * from customer,user,address,fullname,account where user.fullnameid=fullname.id and user.accountid=account.id and user.addressid=address.id and customer.userid=user.id ";

    private CustomerDao() {
    }

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = resultSetToCustomer(rs);
                if (customer != null) {
                    customers.add(customer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerbyID(int id) {
        String sql1 = sql + " AND user.id=? ;";
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
    
        public Customer getCustomerbyAccountname(String accountName) {
        String sql1 = sql + " AND account.Account=? ;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setString(1, accountName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return resultSetToCustomer(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Customer resultSetToCustomer(ResultSet rs) {
        try {
            Customer customer = new Customer();
            customer.setId(rs.getInt("userid"));
            customer.setEmail(rs.getString("email"));
            customer.setPhone(rs.getInt("phone"));

            // Set full name
            FullName fullName = new FullName();
            fullName.setId(rs.getInt("FullNameId"));
            fullName.setFirstName(rs.getString("FirstName"));
            fullName.setMidName(rs.getString("MidName"));
            fullName.setLastName(rs.getString("LastName"));
            customer.setFullname(fullName);
            // Set address
            Address address = new Address();
            address.setId(rs.getInt("AddressId"));
            address.setStreet(rs.getString("Street"));
            address.setDistrict(rs.getString("District"));
            address.setCity(rs.getString("City"));
            customer.setAddress(address);
            // Account
            Account account = new Account();
            account.setId(rs.getInt("AccountId"));
            account.setAccountName(rs.getString("account"));
            account.setPassword(rs.getString("Password"));
            customer.setAccount(account);
            return customer;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
        @Override
    public boolean insert(Customer customer) throws SQLException {
        connection.setAutoCommit(false);
        String insertAccount = "INSERT INTO `account` ( `Account`, `Password`, `IsAdmin`) VALUES ( ?, ?, ?);";
        String insertAddress = "INSERT INTO `address` ( `Street`, `District`, `City`) VALUES ( ?,?,?);";
        String insertFullname = "INSERT INTO `fullname` (`Firstname`, `Midname`, `Lastname`) VALUES ( ?,?,?);";
        String insertUser = "INSERT INTO `user` ( `Phone`, `Email`, `FullNameID`, `AddressID`, `AccountID`) VALUES (?,?,?,?,?);";
        String insertCustomer = "INSERT INTO `customer` (`UserID`) VALUES (?);";

        PreparedStatement ps = connection.prepareStatement(insertAccount,Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer.getAccount().getAccountName());
        ps.setString(2, customer.getAccount().getPassword());
        ps.setInt(3, 0);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
         if (rs.next()) {
               System.out.println("key1 la "+rs.getInt(1));
              customer.getAccount().setId(rs.getInt(1));
            } else {
               System.out.println("co loi xay ra1");
                connection.rollback();
                return false;
            }
        

        PreparedStatement ps2 = connection.prepareStatement(insertAddress,Statement.RETURN_GENERATED_KEYS);
        ps2.setString(1, customer.getAddress().getCity());
        ps2.setString(2, customer.getAddress().getDistrict());
        ps2.setString(3, customer.getAddress().getCity());
        ps2.executeUpdate();
        ResultSet rs2 = ps2.getGeneratedKeys();
         if (rs2.next()) {
                customer.getAddress().setId(rs2.getInt(1));
            } else {
                System.out.println("co loi xay ra2");
                connection.rollback();
                return false;
            }
     

        PreparedStatement ps3 = connection.prepareStatement(insertFullname,Statement.RETURN_GENERATED_KEYS);
        ps3.setString(1, customer.getFullname().getFirstName());
        ps3.setString(2, customer.getFullname().getMidName());
        ps3.setString(3, customer.getFullname().getLastName());
        ps3.executeUpdate();
        ResultSet rs3 = ps3.getGeneratedKeys();
           if (rs3.next()) {
                customer.getFullname().setId(rs3.getInt(1));
            } else {
                  System.out.println("co loi xay ra3");
                connection.rollback();
                return false;
            }
       

        PreparedStatement ps4 = connection.prepareStatement(insertUser,Statement.RETURN_GENERATED_KEYS);
        ps4.setInt(1, customer.getPhone());
        ps4.setString(2, customer.getEmail());
        ps4.setInt(3, customer.getFullname().getId());
        ps4.setInt(4, customer.getAddress().getId());
        ps4.setInt(5, customer.getAccount().getId());
        ps4.executeUpdate(); 
        ResultSet rs4 = ps4.getGeneratedKeys();
           if (rs4.next()) {
                customer.setId(rs4.getInt(1));
            } else {
                  System.out.println("co loi xay ra4");
                connection.rollback();
                return false;
            }
        
        PreparedStatement ps5 = connection.prepareStatement(insertCustomer,Statement.RETURN_GENERATED_KEYS);
         ps5.setInt(1, customer.getId());
         ps5.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        return true;
    }

    public boolean update(Customer customer) {
        return true;

    }

    public boolean delete(int id) {
        return false;
    }
    
    public Customer login(Customer customer){  
        String sql = "SELECT * FROM account WHERE Account=? and Password=? ";
       
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getAccount().getAccountName());
            ps.setString(2, customer.getAccount().getPassword());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Customer resultCustomer = getCustomerbyAccountname(customer.getAccount().getAccountName());
                return resultCustomer;
            }    
        } catch (Exception e) {
         return null;
        }
     return null;
    }

    
}
