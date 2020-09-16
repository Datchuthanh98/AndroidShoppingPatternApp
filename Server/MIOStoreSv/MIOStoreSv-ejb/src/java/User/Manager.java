/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import User.User;

/**
 *
 * @author admin
 */
public class Manager extends User{
    private String salary;

    public Manager() {
    }

    public Manager(int id, FullName fullname, Account account, Address address, int phone, String email) {
        super(id, fullname, account, address, phone, email);
    }
   
}
