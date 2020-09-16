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
public class Payment  implements Serializable {
    private int id;

    private CompanyPayment companyPayment;
    private MethodPayment methodPayment;
    private String statusPayment;

    public Payment() {
    }

    public Payment(int id) {
        this.id=id;
    }

    public Payment(int id, CompanyPayment companyPayment, MethodPayment methodPayment, String statusPayment) {
        this.id = id;
        this.companyPayment = companyPayment;
        this.methodPayment = methodPayment;
        this.statusPayment = statusPayment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompanyPayment getCompanyPayment() {
        return companyPayment;
    }

    public void setCompanyPayment(CompanyPayment companyPayment) {
        this.companyPayment = companyPayment;
    }

    public MethodPayment getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(MethodPayment methodPayment) {
        this.methodPayment = methodPayment;
    }

    public String getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        this.statusPayment = statusPayment;
    }

    
}
