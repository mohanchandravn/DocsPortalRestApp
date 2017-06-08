/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.controller;

import com.docs.portal.beans.authentication.AuthUser;
import com.docs.portal.beans.file.upload.FileUploadReponse;
import com.docs.portal.service.impl.DCSFileService;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author nithinn
 */
@RestController
public class DocumentUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentUploadController.class);
    
    @Autowired
    DCSFileService dcfs = new DCSFileService();

    @RequestMapping(value = "/docs/upload/uploadFile", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FileUploadReponse> uploadFile(@RequestParam(value = "invoiceNumber", required = false) String invoiceNumber, 
        MultipartHttpServletRequest multipartHttpServletRequest, Authentication authentication) throws Exception {
        
        LOGGER.info("******* Start of uploadFile() in controller ***********");
        AuthUser user = (AuthUser) authentication.getPrincipal();
        CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("primaryFile");
        FileUploadReponse fileUploadReponse = dcfs.uploadFile(multipartFile, user.getCompanyName(), invoiceNumber);
        if (fileUploadReponse == null) {
            return new ResponseEntity<FileUploadReponse>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<FileUploadReponse>(fileUploadReponse, HttpStatus.OK);
    }

}
