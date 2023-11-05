/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatbox.dao;

import java.sql.Connection;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author vigneshwaran
 */
public class ConnectionPool {
    
    private static final BasicDataSource dataSource = new BasicDataSource();
    
    static {
        dataSource.setMinIdle(1);
        dataSource.setMaxIdle(5);
        dataSource.setMaxTotal(15);
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/chatbox");
    }
    
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
