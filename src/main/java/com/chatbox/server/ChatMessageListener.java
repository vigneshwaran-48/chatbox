/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chatbox.server;

import com.chatbox.dto.Message;

/**
 *
 * @author vigneshwaran
 */
@FunctionalInterface
public interface ChatMessageListener {
    void onMessage(Message message);
}
