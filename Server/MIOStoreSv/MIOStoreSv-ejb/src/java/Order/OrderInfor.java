/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

import Payment.Payment;
import Shipment.Shipping;
import User.Customer;
import java.util.List;

/**
 *
 * @author admin
 */
public class OrderInfor {
   private int id;
   private Double total;
   private String createDate;
   private Shipping shipping;
   private String statusOrder;
   private Payment payment;
   private Customer customer;
   private List<CartItem> cartitem;
   
   
    public OrderInfor() {
    }

    public OrderInfor(int id, Double total, String createDate, Shipping shipping, String statusOrder, Payment payment, Customer customer) {
        this.id = id;
        this.total = total;
        this.createDate = createDate;
        this.shipping = shipping;
        this.statusOrder = statusOrder;
        this.payment = payment;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartItem> getCartitem() {
        return cartitem;
    }

    public void setCartitem(List<CartItem> cartitem) {
        this.cartitem = cartitem;
    }

    
   
   
   
   
}
