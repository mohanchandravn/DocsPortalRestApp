/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import com.docs.portal.beans.file.upload.FileUploadReponse;
import com.docs.portal.beans.folder.create.FolderResponse;
import java.io.File;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import com.docs.portal.service.DocumentService;
import com.docs.portal.util.ServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nithinn
 */
@Service("fileService")
public class DCSFileService extends DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DCSFileService.class);
    
    @Autowired
    DCSFolderService folderService;
    
    @Autowired
    DCSMetadataCollectionService metadataCollectionService;

    public FileUploadReponse uploadFile(MultipartFile multipartFile, String companyName, String invoiceNumber) {
        
        File fileToUpload = null;
        FileUploadReponse fileUploadReponse = null;
        FolderResponse folderResponse = null;
                
        if (null != multipartFile) {
            String docsURL = getDcsUrl() + DCS_FILE_URL + "data";
            ServiceHelper oServicesHelper = new ServiceHelper();
          
            String authenticatedString = getAuthorization();
            HashMap<String, String> headers = new HashMap<String, String >();
            headers.put("Authorization", authenticatedString);
            
            fileToUpload = new File(TEMP_DIR + multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(fileToUpload);
            } catch (IOException | IllegalStateException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
            
            // Create metadata collection
            String collectionName = METADATA_COLLECTION_CUSTOMER_INVOICES;
            // String fields = companyName + "," + invoiceNumber;
            // metadataCollectionService.createMetadataCollection(collectionName, fields);

            String folderId = folderService.getFolderIdforUser(companyName);
            // Create a new folder if folder does not exists
            if (null == folderId) {
                folderResponse = folderService.createFolder(companyName);
                folderId = folderResponse.getId(); 

                // Assign metadata collection to the folder
                metadataCollectionService.assignAMetadataCollectionToAFolder(folderId, collectionName);
            }
                
            FormDataMultiPart formData = new FormDataMultiPart();
            
            // JSON input parameters
            String jsonInputParams = "{ \"parentID\" : \"" + folderId + "\"}";
            BodyPart inputParamsBodyPart = new BodyPart(jsonInputParams, MediaType.APPLICATION_JSON_TYPE);
            inputParamsBodyPart.setContentDisposition(FormDataContentDisposition.name("jsonInputParameters").build());
            formData.bodyPart(inputParamsBodyPart);
            
            // Metadata values
            String metadataValues = "{ \"collection\" : \"" + collectionName + "\", \"" +
                                        METADATA_FIELD_CUSTOMER_NAME + "\" : \"" + companyName + "\", \"" +
                                        METADATA_FIELD_INVOICE_NUMBER + "\" : \"" + invoiceNumber + 
                                    "\"}";
            BodyPart metadataValuesBodyPart = new BodyPart(metadataValues, MediaType.APPLICATION_JSON_TYPE);
            metadataValuesBodyPart.setContentDisposition(FormDataContentDisposition.name("metadataValues").build());
            formData.bodyPart(metadataValuesBodyPart);
            
            // Primary file
            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", fileToUpload, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name("primaryFile").fileName(fileToUpload.getName()).build());
            formData.bodyPart(fileDataBodyPart);

            formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

            String responseString = oServicesHelper.executePost(docsURL, headers, MediaType.MULTIPART_FORM_DATA_TYPE, formData);

            System.out.println("Output:" + responseString);

            ObjectMapper mapper = new ObjectMapper();
            try {
                fileUploadReponse = mapper.readValue(responseString, FileUploadReponse.class);
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        
        return fileUploadReponse;
    }

}