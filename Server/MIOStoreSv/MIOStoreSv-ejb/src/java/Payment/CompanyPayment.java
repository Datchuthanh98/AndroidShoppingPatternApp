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
public class CompanyPayment  implements Serializable {
    private int id;
    private String nameCompanyPayment;
    private String emailCompanyPayment;
    private String phoneCompanyPayment;

    public CompanyPayment() {
    }

    public CompanyPayment(int id) {
        this.id=id;
    }

    public CompanyPayment(int id, String nameCompanyPayment) {
        this.id = id;
        this.nameCompanyPayment = nameCompanyPayment;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompanyPayment() {
        return nameCompanyPayment;
    }

    public void setNameCompanyPayment(String nameCompanyPayment) {
        this.nameCompanyPayment = nameCompanyPayment;
    }

    public String getEmailCompanyPayment() {
        return emailCompanyPayment;
    }

    public void setEmailCompanyPayment(String emailCompanyPayment) {
        this.emailCompanyPayment = emailCompanyPayment;
    }

    public String getPhoneCompanyPayment() {
        return phoneCompanyPayment;
    }

    public void setPhoneCompanyPayment(String phoneCompanyPayment) {
        this.phoneCompanyPayment = phoneCompanyPayment;
    }
    
    
    
}
