/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.docs.portal.beans.folder.create.FolderResponse;
import com.docs.portal.service.DocumentService;
import com.docs.portal.util.ServiceHelper;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Service;

/**
 *
 * @author nithinn
 */
@Service
public class DCSFileService extends DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DCSFileService.class);

    @Autowired
    DCSFolderService folderService;
    
    @Autowired
    DCSMetadataCollectionService metadataCollectionService;

    public String uploadFile(MultipartFile multipartFile, String companyName, String invoiceNumber) {
        
        File fileToUpload = null;
        String fileUploadReponse = ServiceHelper.STATUS_FAILED;
        FolderResponse folderResponse = null;
        Path tempDir = null;
                        
        if (null != multipartFile) {
            try {
                String docsURL = getDcsUrl() + DCS_FILE_URL + "data";
                ServiceHelper oServicesHelper = new ServiceHelper();

                String authenticatedString = getAuthorization();
                HashMap<String, String> headers = new HashMap<String, String >();
                headers.put("Authorization", authenticatedString);
                
                // Create temporary directory
                tempDir = Files.createTempDirectory(TEMP_DIR);
                // Create temporary file
                fileToUpload = new File(tempDir + "/" + multipartFile.getOriginalFilename());
                multipartFile.transferTo(fileToUpload);
            
                String collectionName = METADATA_COLLECTION_CUSTOMER_INVOICES;

                // Get folder id for the company
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

                oServicesHelper.executePost(docsURL, headers, MediaType.MULTIPART_FORM_DATA_TYPE, formData);
                fileUploadReponse = ServiceHelper.STATUS_SUCCEEDED;

            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
                
            } finally {
                // Delete temporary file
                if (null != fileToUpload && fileToUpload.exists()) {
                    fileToUpload.delete();
                }
                // Delete temporary directory
                if (null != tempDir) {
                    try {
                        Files.deleteIfExists(tempDir);
                    } catch (IOException ex) {
                        LOGGER.error(ex.getMessage(), ex);
                    }
                }
            }
        }
        
        return fileUploadReponse;
    }

    public Map<String, Object> downLoadFile(String fileId, String version) {
        String docsURL = getDcsUrl() + DCS_FILE_URL + "/" + fileId + "/data";
        ServiceHelper oServicesHelper = new ServiceHelper();
        String authenticatedString = getAuthorization();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authenticatedString);

        HashMap<String, String> queryParams = new HashMap<String, String>();
        if (!StringUtils.isEmpty(version)) {
                queryParams.put("version", version);
        }
        Map<String, Object> responseMap = oServicesHelper.executeGetForDownload(docsURL, queryParams, headers, MediaType.APPLICATION_JSON);
        return responseMap;
    }

}
