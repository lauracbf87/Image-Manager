/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nagarro.images.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laura.Barragan
 */
public class ConnectionHelper {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/assigment2";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conn = DriverManager.getConnection(url, "root", "Cristin@8");
        return conn;
    }
    
}
