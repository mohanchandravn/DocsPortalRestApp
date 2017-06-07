/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import java.util.HashMap;
import javax.ws.rs.core.MediaType;

import com.docs.portal.service.DocumentService;
import com.docs.portal.util.ServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nithinn
 */
public class DCSFolderService extends DocumentService {
    
    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    public void createFolder() {
        // TODO
    }

    public void searchFoldersOrFiles() {

        // String docsURL = "<DOCS Instance URL>/documents/api/1.1/folders/search/items";
        String docsURL = getDcsUrl() + DCS_FOLDER_URL + "search/items";
        ServiceHelper oServicesHelper = new ServiceHelper();
        try {
            // String authenticatedString = Authenticate.authenticate("<username>", "<password>"); 
            String authenticatedString = getAuthorization();
            HashMap<String, String> headers = new HashMap<String, String>();
            // headers.put("Authorization", "Basic " + authenticatedString);
            headers.put("Authorization", authenticatedString);
            
            HashMap<String, String> queryParams = new HashMap<String, String>();
            queryParams.put("querytext", "<CustomPropertyName>.<CustomPropertyField><CONTAINS>%60<SearchText>%60");

            String responseString = oServicesHelper.executeGet(docsURL, queryParams, headers, MediaType.APPLICATION_JSON);
            System.out.println(responseString);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void searchFoldersOrFilesByFolderId(String folderId) {
        // TODO
    }

}
