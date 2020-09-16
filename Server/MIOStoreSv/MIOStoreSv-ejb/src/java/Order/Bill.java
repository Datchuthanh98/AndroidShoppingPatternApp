/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;

/**
 *
 * @author admin
 */
public class Bill {
    private int id;
    private OrderInfor order;
    private String createDateBill ;

    public Bill() {
    }

    public Bill(int id, OrderInfor order, String createDateBill) {
        this.id = id;
        this.order = order;
        this.createDateBill = createDateBill;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderInfor getOrder() {
        return order;
    }

    public void setOrder(OrderInfor order) {
        this.order = order;
    }

    public String getCreateDateBill() {
        return createDateBill;
    }

    public void setCreateDateBill(String createDateBill) {
        this.createDateBill = createDateBill;
    }
    
}
