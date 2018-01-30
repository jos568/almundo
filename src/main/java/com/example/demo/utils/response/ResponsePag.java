/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utils.response;

import java.util.List;
import org.springframework.data.domain.Page;


public class ResponsePag<T> {
    
    private Page<T> data = null;
    
    
    private Long totalItems = null;

    public ResponsePag(Page<T> data, Long totalItems  ) {
        
        this.data = data;
        this.totalItems = totalItems;
    }

    
    
    
    

    /**
     * @return the data
     */
    public Page<T> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Page<T> data) {
        this.data = data;
    }

    /**
     * @return the totalItems
     */
    public Long getTotalItems() {
        return totalItems;
    }

    /**
     * @param totalItems the totalItems to set
     */
    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }
    
    
}
