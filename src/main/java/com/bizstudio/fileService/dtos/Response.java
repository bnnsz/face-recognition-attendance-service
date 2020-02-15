/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author obinna.asuzu
 */
public class Response {
    private String date;
    private String message;
    
    public Response(String message) {
        this.date = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss a").format(new Date());
        this.message = message;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

