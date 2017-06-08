/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.beans.folder.create;

import com.docs.portal.beans.common.CreatedBy;
import com.docs.portal.beans.common.ModifiedBy;
import com.docs.portal.beans.common.OwnedBy;

/**
 *
 * @author nithinn
 */
public class FolderResponse {
    
    private String errorCode;

    private String childitemsCount;
       
    private CreatedBy createdBy;

    private String createdTime;
    
    private String description;
    
    private String id;
    
    private ModifiedBy modifiedBy;

    private String modifiedTime;
      
    private String name;
    
    private OwnedBy ownedBy;
    
    private String parentID;
    
    private String size;
    
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
     * @return the childitemsCount
     */
    public String getChilditemsCount() {
        return childitemsCount;
    }

    /**
     * @param childitemsCount the childitemsCount to set
     */
    public void setChilditemsCount(String childitemsCount) {
        this.childitemsCount = childitemsCount;
    }

    /**
     * @return the createdBy
     */
    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdTime
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime the createdTime to set
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the modifiedBy
     */
    public ModifiedBy getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(ModifiedBy modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedTime
     */
    public String getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @param modifiedTime the modifiedTime to set
     */
    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the ownedBy
     */
    public OwnedBy getOwnedBy() {
        return ownedBy;
    }

    /**
     * @param ownedBy the ownedBy to set
     */
    public void setOwnedBy(OwnedBy ownedBy) {
        this.ownedBy = ownedBy;
    }

    /**
     * @return the parentID
     */
    public String getParentID() {
        return parentID;
    }

    /**
     * @param parentID the parentID to set
     */
    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
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
