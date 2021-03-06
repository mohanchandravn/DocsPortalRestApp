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

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);

    // From Environment Variable
    public static final Optional<String> DOCUMENT_CLOUD_SERVICE_USER = Optional.ofNullable(System.getenv("DOCUMENT_CLOUD_SERVICE_USER"));
    public static final Optional<String> DOCUMENT_CLOUD_SERVICE_PWD = Optional.ofNullable(System.getenv("DBAAS_USER_PASSWORD"));
    public static final Optional<String> DOCUMENT_CLOUD_SERVICE_URL = Optional.ofNullable(System.getenv("DOCUMENT_CLOUD_SERVICE_URL"));

    // Local settings        
    public static final String LOCAL_DOCUMENT_CLOUD_SERVICE_USER = "cloud.admin";
    public static final String LOCAL_DOCUMENT_CLOUD_SERVICE_PWD = "extra@4CraWleR";
    public static final String LOCAL_DOCUMENT_CLOUD_SERVICE_URL = "https://documents-usoracleam82569.documents.us2.oraclecloud.com/documents/api/1.1";

    protected final static String DCS_FILE_URL = "/files/";
    protected final static String DCS_FOLDER_URL = "/folders/";
    protected final static String DCS_METADATA_URL = "/metadata/";
    protected final static String DCS_PUBLIC_LINK_URL = "/publiclinks/file/";
    protected final static String DCS_APPLINK_URL = "/applinks/";
    
    protected final static String METADATA_COLLECTION_CUSTOMER_INVOICES = "CustomerInvoices";

    protected final static String METADATA_FIELD_CUSTOMER_NAME = "CustomerName";
    protected final static String METADATA_FIELD_INVOICE_NUMBER = "InvoiceNumber";
    protected final static String METADATA_FIELD_INVOICE_DATE = "InvoiceDate";

    protected final static String TEMP_DIR = "file-upload-temp-dir";
        
    private String dcsUrl = null;
    protected String authorization = null;

    public DocumentService() {
        try {
            this.setDcsUrl(DOCUMENT_CLOUD_SERVICE_URL.orElse(LOCAL_DOCUMENT_CLOUD_SERVICE_URL));
            this.setAuthorization(DOCUMENT_CLOUD_SERVICE_USER.orElse(LOCAL_DOCUMENT_CLOUD_SERVICE_USER) + ":" + DOCUMENT_CLOUD_SERVICE_PWD.orElse(LOCAL_DOCUMENT_CLOUD_SERVICE_PWD));

        } catch (Exception exp) {
            LOGGER.error("exp: " + exp.getMessage());
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
        this.authorization = "Basic " + new String(Base64.encode(authorization)); // "cloud.admin:extra@4CraWleR"
    }

    /**
     * @return the dcsUrl
     */
    public String getDcsUrl() {
        return dcsUrl;
    }

    /**
     * @param dcsUrl the dcsUrl to set
     */
    public void setDcsUrl(String dcsUrl) {
        this.dcsUrl = dcsUrl;
    }

}
