package Payment;


import Payment.Payment;
import Payment.MethodPayment;
import Dao.Dao;
import Dao.Dao;
import Shipment.Shipping;
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
public class PaymentDao extends Dao implements PaymentInterface{
    private static volatile PaymentDao instance;
        String dkPay = "payment.companypaymentid=companypayment.id and methodpaymentid=methodpayment.id ";
       String tablepay="payment,companypayment,methodpayment";
       private  String sql = "select DISTINCT * from "+tablepay+" where "+dkPay;
    
    private PaymentDao(){}
    
      public static PaymentDao getInstance(){
       if (instance == null){
           instance = new PaymentDao();
       }
       return instance;
   }
      
   public List<Payment> getAll(){
      List<Payment> payments = new ArrayList<>();
      try{
          PreparedStatement ps = connection.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          while (rs.next()){  
             Payment payment = resultSetToCustomer(rs);
           if(payment!=null){
              payments.add(payment);
           }
          }
      }catch(Exception e){
          e.printStackTrace();
      }
      return payments;
   }
   
   
     public Payment getPaymentbyID(int id){
      String sql1 = sql+" AND payment.idpayment=? ;";
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
   
       public int insert(Payment payment) throws SQLException {
        connection.setAutoCommit(false);
        String insertPayment = "INSERT INTO `payment` ( `CompanyPaymentID`, `MethodPaymentID`, `StatusPayment`) VALUES ( ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(insertPayment, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, payment.getCompanyPayment().getId());
        ps.setInt(2, payment.getMethodPayment().getId());
        ps.setString(3, payment.getStatusPayment());    
        ps.executeUpdate();
        int idPayment=0;
        ResultSet rs = ps.getGeneratedKeys();
          if (rs.next()) {
           idPayment=rs.getInt(1);
            //toto add cartItem
        } else {
            System.out.println("co loi xay ra1");
            connection.rollback();
            return 0;
        }
        connection.commit();
        connection.setAutoCommit(true);
        return idPayment;
    }
       
   
   private Payment resultSetToCustomer(ResultSet rs) {
        try {Payment payment = new Payment();
            payment.setId(rs.getInt("idPayment"));
            CompanyPayment companyPayment = new CompanyPayment();
            companyPayment.setNameCompanyPayment("namecompanypayment");
            payment.setCompanyPayment(companyPayment);
            MethodPayment methodPayment = new MethodPayment();
            methodPayment.setNameMethodPayment(rs.getString("namemethodpayment"));
            payment.setMethodPayment(methodPayment);
            payment.setStatusPayment("statusPayment");
          
            return payment;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 

}
