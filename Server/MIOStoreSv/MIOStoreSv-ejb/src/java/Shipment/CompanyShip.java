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
public class CompanyShip  implements Serializable {
    private int id;
    private String nameCompanyShip;
    private String email;
    private int phone;
    private float feeNormal;
    private float feeFast;

    public CompanyShip() {
    }

    public CompanyShip(int id) {
        this.id=id;
    }

    public CompanyShip(int id, String nameCompanyShip, String email, int phone, float feeNormal, float feeFast) {
        this.id = id;
        this.nameCompanyShip = nameCompanyShip;
        this.email = email;
        this.phone = phone;
        this.feeNormal = feeNormal;
        this.feeFast = feeFast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompanyShip() {
        return nameCompanyShip;
    }

    public void setNameCompanyShip(String nameCompanyShip) {
        this.nameCompanyShip = nameCompanyShip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public float getFeeNormal() {
        return feeNormal;
    }

    public void setFeeNormal(float feeNormal) {
        this.feeNormal = feeNormal;
    }

    public float getFeeFast() {
        return feeFast;
    }

    public void setFeeFast(float feeFast) {
        this.feeFast = feeFast;
    }
    
    
          
}
