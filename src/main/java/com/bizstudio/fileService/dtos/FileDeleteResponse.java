/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.dtos;

/**
 *
 * @author obinna.asuzu
 */
public class FileDeleteResponse {
    private boolean successful;
    private String filename;

    public FileDeleteResponse() {
    }

    public FileDeleteResponse(boolean successful, String filename) {
        this.successful = successful;
        this.filename = filename;
    }
    
    
    /**
     * @return the successful
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * @param successful the successful to set
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    
}
