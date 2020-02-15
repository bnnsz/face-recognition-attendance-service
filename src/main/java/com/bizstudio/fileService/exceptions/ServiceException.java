/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.exceptions;

import com.bizstudio.fileService.enums.ErrorStatus;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author obinna.asuzu
 */
public class ServiceException extends Exception{
    ErrorStatus error;
    private Map<String,String> params = new HashMap<>();
    

    public ServiceException(ErrorStatus code) {
        this.error = code;
    }
    
    public ServiceException(ErrorStatus code, Map<String,String> params) {
        this.error = code;
        this.params = params;
    }
    
    

    @Override
    public String getMessage() {
        return error.getMessage();
    }
    
    public ErrorStatus getError() {
        return error;
    }

    /**
     * @return the params
     */
    public Map<String,String> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Map<String,String> params) {
        this.params = params;
    }
}

