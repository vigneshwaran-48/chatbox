/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatbox.exception;

/**
 *
 * @author vigneshwaran
 */
public class AppException extends Exception {
        
    public AppException(String message) {
        super(message);
    } 
    
    public AppException() {
        this("Internel Error");
    }
    
}
