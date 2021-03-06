/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.controller;

import com.docs.portal.service.impl.DCSFileService;
import java.util.Date;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    public String uploadFile(@RequestParam(value = "companyName", required = false) String companyName,
        @RequestParam(value = "invoiceNumber", required = false) String invoiceNumber, 
        @RequestParam(value = "invoiceDate", required = false) Date invoiceDate,
        MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        
        LOGGER.info("******* Start of uploadFile() in controller ***********");
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("primaryFile");
        return dcfs.uploadFile(multipartFile, companyName, invoiceNumber, invoiceDate);
    }

}
