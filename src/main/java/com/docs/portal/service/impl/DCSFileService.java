/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import java.io.File;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import com.docs.portal.service.DocumentService;
import com.docs.portal.util.ServiceHelper;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

/**
 *
 * @author nithinn
 */
public class DCSFileService extends DocumentService {

    public void fileUpload() {
        
        String docsURL = "<DOCS Instance URL>/documents/api/1.1/files/data";
        ServiceHelper oServicesHelper = new ServiceHelper();

        try {
            File fileToUpload = new File("<File PATH>");
            String authenticatedString = ""; // TODO Authenticate.authenticate("<username>", "<password>");

            HashMap<String, String> headers = new HashMap<String, String >();
            headers.put("Authorization", "Basic " + authenticatedString);

            String jsonInput = "{"
                    + "\"parentID\":\"<FOLDER ID>\""
                    + "}";

            FormDataMultiPart formData = new FormDataMultiPart();
            BodyPart stringDataBodyPart = new BodyPart(jsonInput, MediaType.APPLICATION_JSON_TYPE);
            stringDataBodyPart.setContentDisposition(FormDataContentDisposition.name("jsonInputParameters").build());

            formData.bodyPart(stringDataBodyPart);

            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart("file", fileToUpload, MediaType.APPLICATION_OCTET_STREAM_TYPE);
            fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name("primaryFile").fileName(fileToUpload.getName()).build());
            formData.bodyPart(fileDataBodyPart);

            formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

            String responseString = oServicesHelper.ExecutePost(docsURL, headers, MediaType.MULTIPART_FORM_DATA_TYPE, formData);

            System.out.println("Output:" + responseString);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
