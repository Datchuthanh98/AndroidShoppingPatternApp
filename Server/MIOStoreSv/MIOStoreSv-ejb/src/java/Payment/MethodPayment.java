/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class MethodPayment  implements Serializable {
    private int id;
    private String nameMethodPayment;

    public MethodPayment() {
    }

    public MethodPayment(int id) {
        this.id=id;
    }

    public MethodPayment(int id, String nameMethodPayment) {
        this.id = id;
        this.nameMethodPayment = nameMethodPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMethodPayment() {
        return nameMethodPayment;
    }

    public void setNameMethodPayment(String nameMethodPayment) {
        this.nameMethodPayment = nameMethodPayment;
    }
    
}
