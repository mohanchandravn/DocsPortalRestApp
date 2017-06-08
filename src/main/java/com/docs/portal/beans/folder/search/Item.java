/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.beans.folder.search;

import com.docs.portal.beans.common.CreatedBy;
import com.docs.portal.beans.common.ModifiedBy;
import com.docs.portal.beans.common.OwnedBy;

/**
 *
 * @author mohchand
 */
public class Item {

    private String type;
    private String id;
    private String name;
    private String parentID;
    private String createdTime;
    private String modifiedTime;
    private CreatedBy createdBy;
    private OwnedBy ownedBy;
    private ModifiedBy modifiedBy;
    private String size;
    private String version;
    private String description;

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
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
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
    
}
