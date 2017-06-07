/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.controller;

import com.docs.portal.beans.authentication.AuthUser;
import com.docs.portal.beans.search.SearchResponse;
import com.docs.portal.service.impl.DCSFolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 *
 * @author mohchand
 */
@RestController
public class DocumentSearchController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentSearchController.class);

    @RequestMapping(value = "/docs/search/searchFiles", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SearchResponse> searchFiles(@RequestParam(value = "invoiceNumber",
            required = false) String invoiceNumber, Authentication authentication) throws Exception {
        logger.info("******* Start of searchFiles() in controller ***********");
        AuthUser user = (AuthUser) authentication.getPrincipal();
        DCSFolderService dcfs = new DCSFolderService();
        SearchResponse searchResponse = dcfs.getFilesForUser(user.getCompanyName(), invoiceNumber, null, null, null, null);
        if (searchResponse == null) {
        	return new ResponseEntity<SearchResponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<SearchResponse>(searchResponse, HttpStatus.OK);
    }

}
