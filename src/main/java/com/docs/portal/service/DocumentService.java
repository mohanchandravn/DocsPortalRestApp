/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.jersey.core.util.Base64;

/**
 *
 * @author nithinn
 */
public abstract class DocumentService {
    
    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
    // From Environment Variable
    public static final Optional<String> DOCUMENT_CLOUD_SERVICE_USER = Optional.ofNullable(System.getenv("DOCUMENT_CLOUD_SERVICE_USER"));
    public static final Optional<String> DOCUMENT_CLOUD_SERVICE_PWD = Optional.ofNullable(System.getenv("DBAAS_USER_PASSWORD"));
    public static final Optional<String> DOCUMENT_CLOUD_SERVICE_URL = Optional.ofNullable(System.getenv("DOCUMENT_CLOUD_SERVICE_URL"));

    //Local settings        
    public static final String LOCAL_DOCUMENT_CLOUD_SERVICE_USER = "cloud.admin";
    public static final String LOCAL_DOCUMENT_CLOUD_SERVICE_PWD = "extra@4CraWleR";
    public static final String LOCAL_DOCUMENT_CLOUD_SERVICE_URL = "https://documents-usoracleam82569.documents.us2.oraclecloud.com/documents/api/1.1";
    
    protected String DCS_URL = null;
    protected String authorization;
    protected final static String DCS_PUBLIC_LINK_URL = "/publiclinks/file/";
    protected final static String DCS_FILE_URL = "/files";
    protected final static String DCS_FOLDER_URL = "/folders/";
    protected final static String DCS_APPLINK_URL = "/applinks";
    
    public DocumentService() {
        try {
            this.setAuthorization(DOCUMENT_CLOUD_SERVICE_USER.orElse(LOCAL_DOCUMENT_CLOUD_SERVICE_USER) + ":" + DOCUMENT_CLOUD_SERVICE_PWD.orElse(LOCAL_DOCUMENT_CLOUD_SERVICE_PWD));
            this.setDCS_URL(DOCUMENT_CLOUD_SERVICE_URL.orElse(LOCAL_DOCUMENT_CLOUD_SERVICE_USER));
        } catch (Exception exp) {
            logger.error("exp:" + exp.getMessage());
        }
    }

    /**
     * @return the authorization
     */
    public String getAuthorization() {
        return authorization;
    }

    /**
     * @param authorization the authorization to set
     */
    public void setAuthorization(String authorization) {
        this.authorization = "Basic " + new String(Base64.encode(authorization));;//"cloud.admin:extra@4CraWleR"
    }

    /**
     * @return the DCS_URL
     */
    public String getDCS_URL() {
        return DCS_URL;
    }

    /**
     * @param DCS_URL the DCS_URL to set
     */
    public void setDCS_URL(String DCS_URL) {
        this.DCS_URL = DCS_URL;
    }
    
}
