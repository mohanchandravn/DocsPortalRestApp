/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.controller;

import com.docs.portal.beans.authentication.AuthUser;
import com.docs.portal.beans.folder.search.SearchResponse;
import com.docs.portal.service.impl.DCSFileService;
import com.docs.portal.service.impl.DCSFolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

/**
 *
 * @author mohchand
 */


@RestController
public class DocumentSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentSearchController.class);

    @RequestMapping(value = "/docs/search/searchFiles", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<SearchResponse> searchFiles(
        @RequestParam(value = "invoiceNumber", required = false) String invoiceNumber,
        Authentication authentication) throws Exception {

        LOGGER.info("******* Start of searchFiles() in controller ***********");
        SearchResponse searchResponse = null;
        AuthUser user = (AuthUser) authentication.getPrincipal();
        String userRole = user.getAuthority();
        DCSFolderService dcfs = new DCSFolderService();
        if (userRole.replace("ROLE_", "").equalsIgnoreCase("ADMIN")) {
        	searchResponse = dcfs.getFilesForAdmin(invoiceNumber, null, null, null, null);
        } else {
        	searchResponse = dcfs.getFilesForUser(user.getCompanyName(), invoiceNumber, null, null, null,
                    null);
        }

        if (searchResponse == null) {
                return new ResponseEntity<SearchResponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<SearchResponse>(searchResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/docs/download/downloadDocument", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void downloadDocument(@RequestParam(value = "fileId", required = true) String fileId,
        @RequestParam(value = "version", required = true) String version, HttpServletResponse response) throws Exception {
        
        LOGGER.info("******* Start of searchFiles() in controller ***********");

        DCSFileService dcfs = new DCSFileService();
        Map<String, Object> searchResponse = dcfs.downLoadFile(fileId, version);
        String fileName = (String) searchResponse.get("fileName");
        String fileType = (String) searchResponse.get("contentType");
        InputStream inpStr = (InputStream) searchResponse.get("inputStream");
        response.setContentType(fileType);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        org.apache.commons.io.IOUtils.copy(inpStr, response.getOutputStream());
        response.flushBuffer();

        LOGGER.info("******* End of searchFiles() in controller ***********");
    }

}
