/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.chatbox.dto.*;
import com.chatbox.exception.AppException;
/**
 *
 * @author vigneshwaran
 */
public class UserServiceImpl implements UserService {
 
    @Override
    public boolean isValidUser(String userName, String password)  throws AppException {
        
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(UserMeta.TABLE).append(" ")
                .append("WHERE ").append(UserMeta.NAME)
                .append(" = ? && ").append(UserMeta.PASSWORD)
                .append(" = ?");
        
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setString(1, userName);
            statement.setString(2, password);
            
            ResultSet result = statement.executeQuery();
            return result.next();
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public long addUser(String userName, String password) throws AppException {
        StringBuffer buffer = new StringBuffer("INSERT INTO ");
        buffer.append(UserMeta.TABLE).append(" (").append(UserMeta.NAME)
                .append(", ").append(UserMeta.PASSWORD)
                .append(") VALUES (?, ?)");
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(buffer.toString());
            statement.setString(1, userName);
            statement.setString(2, password);
            
            statement.executeUpdate();
            
            Optional<User> addedUser = findByUserName(userName);
            if(addedUser.isPresent()) {
                return addedUser.get().getId();
            }
            throw new InternalError("Error while creating user");
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByUserName(String userName) throws AppException {
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(UserMeta.TABLE).append(" ")
                .append("WHERE ").append(UserMeta.NAME)
                .append(" = ?");
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setString(1, userName);
            
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                User user = new User(result.getInt(UserMeta.ID), result.getString(UserMeta.NAME));
                return Optional.of(user);
            }
            return Optional.empty();         
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findUserById(long id) throws AppException {
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(UserMeta.TABLE).append(" ")
                .append("WHERE ").append(UserMeta.ID)
                .append(" = ?");
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement.setLong(1, id);
            
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                User user = new User(result.getInt(UserMeta.ID), result.getString(UserMeta.NAME));
                return Optional.of(user);
            }
            return Optional.empty();
         
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }
    
    
}
