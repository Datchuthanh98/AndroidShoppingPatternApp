package Dao;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public abstract class Dao {
    protected Connection connection;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/miostore2"+"?useUnicode=true&characterEncoding=UTF-8";
    private String username = "root";
    private String password = "";
    
    protected Dao(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("DB Connection: Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
