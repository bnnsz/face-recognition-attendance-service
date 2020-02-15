/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bizstudio.fileService.dtos;

import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author obinna.asuzu
 */
public class QueryResult<T> {

    private List<T> items;
    private long totalCount;
    private String errorMessage;

    public QueryResult() {
    }
    
    public QueryResult(Page<T> result) {
        items = result.getContent();
        totalCount = result.getTotalElements();
    }

    /**
     * @return the items
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * @return the totalCount
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
