/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.beans.metadata.create;

/**
 *
 * @author nithinn
 */
public class FolderCollectionResponse {
       
    private String errorCode;
    private String idList;
    private String type;

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the idList
     */
    public String getIdList() {
        return idList;
    }

    /**
     * @param idList the idList to set
     */
    public void setIdList(String idList) {
        this.idList = idList;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
