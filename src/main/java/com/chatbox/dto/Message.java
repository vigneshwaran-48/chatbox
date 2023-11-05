package com.chatbox.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Message {
    
    private String clientName;
    private String message;

    public Message(String clientName, String message) {
        this.clientName = clientName;
        this.message = message;
    }
    
    public Message() {}

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String str = "";

        ObjectMapper mapper = new ObjectMapper();
        try {
            str = mapper.writeValueAsString(this);
        } 
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return str;
    }
    
    public static Message toMessage(String jsonString) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Message.class);
    }

}
