/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shipment;

import Shipment.Shipping;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ShippingInterface {
     List<Shipping> getAll();
     Shipping getShippingbyID(int id);
}
