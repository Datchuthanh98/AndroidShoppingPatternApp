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
public class MethodShipping  implements Serializable {
    private int id;
    private String nameMethodShip;

    public MethodShipping() {
    }

    public MethodShipping(int id) {
        this.id=id;
    }

    public MethodShipping(int id, String nameMethodShip) {
        this.id = id;
        this.nameMethodShip = nameMethodShip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameMethodShip() {
        return nameMethodShip;
    }

    public void setNameMethodShip(String nameMethodShip) {
        this.nameMethodShip = nameMethodShip;
    }
    
    
            
}
