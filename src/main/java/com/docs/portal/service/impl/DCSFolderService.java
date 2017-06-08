/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import com.docs.portal.beans.folder.create.FolderResponse;
import com.docs.portal.beans.folder.search.Item;
import com.docs.portal.beans.folder.search.SearchResponse;

import java.util.HashMap;
import javax.ws.rs.core.MediaType;

import com.docs.portal.service.DocumentService;
import com.docs.portal.util.ServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 *
 * @author nithinn
 */
public class DCSFolderService extends DocumentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DCSFolderService.class);

    public FolderResponse createFolder(String companyName) {
        
        FolderResponse folderResponse = null;
        
        String docsURL = getDcsUrl() + DCS_FOLDER_URL + "<FOLDER_ID>";
        ServiceHelper oServicesHelper = new ServiceHelper();
        
        String authenticatedString = getAuthorization();

        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authenticatedString);
        
        String jsonInput = "{ \"name\" : " + companyName + ",\"description\":\"<DESCRIPTION>\"}";

        String responseString = oServicesHelper.executePost(docsURL, headers, MediaType.APPLICATION_JSON_TYPE, jsonInput);

        System.out.println("Output:" + responseString);

        ObjectMapper mapper = new ObjectMapper();
        try {
            folderResponse = mapper.readValue(responseString, FolderResponse.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        
        return folderResponse;
    }

    public SearchResponse searchFoldersOrFilesWithFullText(String fullText) {
        return searchFoldersOrFiles(fullText, null, null, null, null);
    }

    public SearchResponse searchFoldersOrFilesWithFullText(String fullText, String limit) {
        return searchFoldersOrFiles(fullText, limit, null, null, null);
    }

    public SearchResponse searchFoldersOrFilesWithFullText(String fullText, String limit, String offset) {
        return searchFoldersOrFiles(fullText, limit, offset, null, null);
    }

    public SearchResponse searchFoldersOrFilesWithFullText(String fullText, String limit, String offset, String orderBy) {
        return searchFoldersOrFiles(fullText, limit, offset, orderBy, null);
    }

    public SearchResponse searchFoldersOrFiles(String fullText, String limit, String offset, String orderBy, String queryText) {

        String docsURL = getDcsUrl() + DCS_FOLDER_URL + "search/items";
        ServiceHelper oServicesHelper = new ServiceHelper();
        SearchResponse searchResponse = null;

        String authenticatedString = getAuthorization();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authenticatedString);

        HashMap<String, String> queryParams = new HashMap<String, String>();
//            queryParams.put("querytext", "<CustomPropertyName>.<CustomPropertyField><CONTAINS>%60<SearchText>%60");
        if (!StringUtils.isEmpty(fullText)) {
            queryParams.put("fulltext", fullText);
        }
        if (!StringUtils.isEmpty(limit)) {
            queryParams.put("limit", limit);
        }
        if (!StringUtils.isEmpty(offset)) {
            queryParams.put("offset", offset);
        }
        if (!StringUtils.isEmpty(orderBy)) {
            queryParams.put("orderBy", orderBy);
        }
        if (!StringUtils.isEmpty(queryText)) {
            queryParams.put("querytext", queryText);
        }
        
        if (!StringUtils.isEmpty(fullText) && !StringUtils.isEmpty(queryText)) {
            queryParams.put("fulltext", "");
        }
        String responseString = oServicesHelper.executeGet(docsURL, queryParams, headers, MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        try {
            searchResponse = mapper.readValue(responseString, SearchResponse.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return searchResponse;
    }

    public SearchResponse searchFoldersOrFilesByFolderIdWithFullText(String fullText, String folderId) {
        return searchFoldersOrFilesByFolderId(fullText, null, null, null, null, folderId);
    }

    public SearchResponse searchFoldersOrFilesByFolderIdWithFullText(String fullText, String limit, String folderId) {
        return searchFoldersOrFilesByFolderId(fullText, limit, null, null, null, folderId);
    }

    public SearchResponse searchFoldersOrFilesByFolderIdWithFullText(String fullText, String limit, String offset, String folderId) {
        return searchFoldersOrFilesByFolderId(fullText, limit, offset, null, null, folderId);
    }

    public SearchResponse searchFoldersOrFilesByFolderIdWithFullText(String fullText, String limit, String offset, String orderBy, String folderId) {
        return searchFoldersOrFilesByFolderId(fullText, limit, offset, orderBy, null, folderId);
    }

    public SearchResponse searchFoldersOrFilesByFolderId(String fullText, String limit, String offset, String orderBy, String queryText, String folderId) {
        String docsURL = getDcsUrl() + DCS_FOLDER_URL + folderId + "/search/items";
        ServiceHelper oServicesHelper = new ServiceHelper();
        SearchResponse searchResponse = null;

        String authenticatedString = getAuthorization();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authenticatedString);

        HashMap<String, String> queryParams = new HashMap<String, String>();
//            queryParams.put("querytext", "<CustomPropertyName>.<CustomPropertyField><CONTAINS>%60<SearchText>%60");
        if (!StringUtils.isEmpty(fullText)) {
            queryParams.put("fulltext", fullText);
        }
        if (!StringUtils.isEmpty(limit)) {
            queryParams.put("limit", limit);
        }
        if (!StringUtils.isEmpty(offset)) {
            queryParams.put("offset", offset);
        }
        if (!StringUtils.isEmpty(orderBy)) {
            queryParams.put("orderBy", orderBy);
        }
        if (!StringUtils.isEmpty(queryText)) {
            queryParams.put("querytext", queryText);
        }
        if (StringUtils.isEmpty(fullText) && StringUtils.isEmpty(queryText)) {
            queryParams.put("fulltext", "");
        }
        String responseString = oServicesHelper.executeGet(docsURL, queryParams, headers, MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        try {
            searchResponse = mapper.readValue(responseString, SearchResponse.class);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return searchResponse;
    }

    public String getFolderIdforUser(String companyName) {
        String folderId = null;
        SearchResponse foldersResponse = searchFoldersOrFilesWithFullText(companyName);
        if (null != foldersResponse & foldersResponse.getItems().size() > 0) {
            for (Item searchItem : foldersResponse.getItems()) {
                if (searchItem.getType().equalsIgnoreCase("folder")) {
                    folderId = searchItem.getId();
                    break;
                }
            }
        }
        return folderId;
    }

    public SearchResponse getFilesForUser(String companyName, String fullText, String limit, String offset, String orderBy, String queryText) {
        SearchResponse filesResponse = null;
        String folderId = getFolderIdforUser(companyName);
        if (null != folderId) {
            filesResponse = searchFoldersOrFilesByFolderId(fullText, limit, offset, orderBy, queryText, folderId);
        }
        return filesResponse;
    }

}
