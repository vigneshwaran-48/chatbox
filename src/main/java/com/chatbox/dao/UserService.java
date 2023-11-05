/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatbox.dao;

import com.chatbox.dto.User;
import com.chatbox.exception.AppException;
import java.util.Optional;

/**
 *
 * @author vigneshwaran
 */
public interface UserService {
    
    boolean isValidUser(String userName, String password) throws AppException;
    
    long addUser(String userName, String password) throws AppException;
    
    Optional<User> findByUserName(String userName) throws AppException;
    
    Optional<User> findUserById(long id) throws AppException;
    
    static UserService getInstance() {
        return new UserServiceImpl();
    }
}
