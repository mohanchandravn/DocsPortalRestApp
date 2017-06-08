/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import com.docs.portal.beans.metadata.create.CollectionCreateResponse;
import com.docs.portal.beans.metadata.create.FileCollectionResponse;
import com.docs.portal.beans.metadata.create.FolderCollectionResponse;
import com.docs.portal.service.DocumentService;
import com.docs.portal.util.ServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author nithinn
 */
@Service("metadataCollectionService")
public class DCSMetadataCollectionService extends DocumentService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DCSMetadataCollectionService.class);

    public CollectionCreateResponse createMetadataCollection(String collectionName, String fields) {
        
        CollectionCreateResponse metadataCreateResponse = null;
        
        String docsURL = getDcsUrl() + DCS_METADATA_URL + collectionName;
        ServiceHelper oServicesHelper = new ServiceHelper();
        
        String authenticatedString = getAuthorization();

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authenticatedString);
        
        String jsonInput = "{ \"fields\" : " + fields + "}";

        String responseString = oServicesHelper.executePost(docsURL, headers, MediaType.APPLICATION_JSON_TYPE, jsonInput);

        System.out.println("Output:" + responseString);

        ObjectMapper mapper = new ObjectMapper();
        try {
            metadataCreateResponse = mapper.readValue(responseString, CollectionCreateResponse.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        
        return metadataCreateResponse;
    }
    
    public FileCollectionResponse assignAMetadataCollectionToAFolder(String collectionName) {
        return assignAMetadataCollectionToAFolder("self", collectionName);
    }
    
    public FileCollectionResponse assignAMetadataCollectionToAFolder(String folderId, String collectionName) {
        
        FileCollectionResponse fileCollectionResponse = null;

        String docsURL = getDcsUrl() + DCS_FOLDER_URL + folderId + DCS_METADATA_URL + collectionName;
        ServiceHelper oServicesHelper = new ServiceHelper();
        
        String authenticatedString = getAuthorization();

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authenticatedString);
        
        String responseString = oServicesHelper.executePost(docsURL, headers, null, "");

        System.out.println("Output:" + responseString);

        ObjectMapper mapper = new ObjectMapper();
        try {
            fileCollectionResponse = mapper.readValue(responseString, FileCollectionResponse.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        
        return fileCollectionResponse;
    }
    
    public FolderCollectionResponse assignAMetadataCollectionToAFile(String fileId, String collectionName) {
        
        FolderCollectionResponse folderCollectionResponse = null;

        String docsURL = getDcsUrl() + DCS_FILE_URL + fileId + DCS_METADATA_URL + collectionName;
        ServiceHelper oServicesHelper = new ServiceHelper();
        
        String authenticatedString = getAuthorization();

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authenticatedString);
        
        String responseString = oServicesHelper.executePost(docsURL, headers, null, "");

        System.out.println("Output:" + responseString);

        ObjectMapper mapper = new ObjectMapper();
        try {
            folderCollectionResponse = mapper.readValue(responseString, FolderCollectionResponse.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        
        return folderCollectionResponse;
    }
    
}
