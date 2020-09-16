/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shipment;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Shipping  implements Serializable {
    private int id;
    private String startShipping;
    private String addressShipping;
    private String shippingStatus;
    private MethodShipping methodShipping;
    private CompanyShip companyShip;

    public Shipping() {
    }

    public Shipping(int id, String startShipping, String addressShipping, String shippingStatus, MethodShipping methodShipping, CompanyShip companyShip) {
        this.id = id;
        this.startShipping = startShipping;
        this.addressShipping = addressShipping;
        this.shippingStatus = shippingStatus;
        this.methodShipping = methodShipping;
        this.companyShip = companyShip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartShipping() {
        return startShipping;
    }

    public void setStartShipping(String startShipping) {
        this.startShipping = startShipping;
    }

    public String getAddressShipping() {
        return addressShipping;
    }

    public void setAddressShipping(String addressShipping) {
        this.addressShipping = addressShipping;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public MethodShipping getMethodShipping() {
        return methodShipping;
    }

    public void setMethodShipping(MethodShipping methodShipping) {
        this.methodShipping = methodShipping;
    }

    public CompanyShip getCompanyShip() {
        return companyShip;
    }

    public void setCompanyShip(CompanyShip companyShip) {
        this.companyShip = companyShip;
    }

    
   
           
          
    
}
