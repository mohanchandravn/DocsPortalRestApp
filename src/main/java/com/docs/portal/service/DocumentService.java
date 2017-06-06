/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service;

/**
 *
 * @author nithinn
 */
public abstract class DocumentService {
       
    private final static String DOCUMENT_CLOUD_SERVICE_URL_KEY = "DOCUMENT_CLOUD_SERVICE_URL";
    private final static String DOCUMENT_CLOUD_SERVICE_USER_KEY = "DOCUMENT_CLOUD_SERVICE_USER";
    private final static String DOCUMENT_CLOUD_SERVICE_PWD_KEY = "DOCUMENT_CLOUD_SERVICE_PWD";
    private String DCS_URL = null;
    private final static String DCS_FILE_URL = "/files";
    private final static String DCS_FOLDER_URL = "/folders/";
    
    public DocumentService() {
        super();
    }

}
