/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.beans.file.upload;

import com.docs.portal.beans.common.CreatedBy;
import com.docs.portal.beans.common.ModifiedBy;
import com.docs.portal.beans.common.OwnedBy;
import com.docs.portal.beans.common.ReservedBy;

/**
 *
 * @author nithinn
 */
public class FileUploadReponse {
       
    private String errorCode;
    
    private String errorMessage;
    
    private CreatedBy createdBy;

    private String createdTime;

    private String id;
    
    private String mimeType;

    private ModifiedBy modifiedBy;

    private String modifiedTime;
    
    private String name;

    private OwnedBy ownedBy;
    
    private String parentID;
    
    private String reservationTime;
    
    private ReservedBy reservedBy;
    
    private String size;
    
    private String type;

    private String version;

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
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
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
     * @return the reservationTime
     */
    public String getReservationTime() {
        return reservationTime;
    }

    /**
     * @param reservationTime the reservationTime to set
     */
    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    /**
     * @return the reservedBy
     */
    public ReservedBy getReservedBy() {
        return reservedBy;
    }

    /**
     * @param reservedBy the reservedBy to set
     */
    public void setReservedBy(ReservedBy reservedBy) {
        this.reservedBy = reservedBy;
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

}
