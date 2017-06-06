/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.PartialRequestBuilder;
import com.sun.jersey.api.client.RequestBuilder;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.multipart.FormDataMultiPart;

/**
 *
 * @author nithinar
 */
public class ServiceHelper {

    public ServiceHelper() {
        super();
    }

    private Client rsclient;

    private Client getClient() {
        if (rsclient != null) {
            return rsclient;
        }
        Client rsclient = Client.create();
        return rsclient;
    }

    public String ExecuteDelete(String url, HashMap<String, String> headers, String mediaType) {
        
        Client rsclient = this.getClient();
        Builder build = rsclient.resource(url).getRequestBuilder();
        ClientResponse clientResponse = null;
        String response = null;
        
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            build.header(entry.getKey(), entry.getValue());
        }

        if (mediaType != null) {
            build.type(mediaType);
        }

        try {
            clientResponse = build.delete(ClientResponse.class);
            if (clientResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + clientResponse.getStatus());
            }
            response = clientResponse.getEntity(String.class);
            
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    public String ExecuteGet(String url, HashMap<String, String> queryParams, HashMap<String, String> headers, String mediaType) {

        Client rsclient = this.getClient();
        WebResource webResource = null;
        if (queryParams != null) {
            webResource = rsclient.resource(url);
            for (Map.Entry<String, String> query : queryParams.entrySet()) {
                webResource = webResource.queryParam(query.getKey(), query.getValue());
            }
        } else {
            webResource = rsclient.resource(url);
        }

        Builder build = webResource.getRequestBuilder();
        ClientResponse clientResponse = null;
        String response = null;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            build.header(entry.getKey(), entry.getValue());
        }
        if (mediaType != null) {
            build.type(mediaType);
        }

        try {
            clientResponse = build.get(ClientResponse.class);
            if (clientResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + clientResponse.getStatus());
            }
            response = clientResponse.getEntity(String.class);
            
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    public String ExecutePost(String url, HashMap<String, String> headers, MediaType mediaType, FormDataMultiPart formData) {

        Client rsclient = this.getClient();
        Builder build = rsclient.resource(url).getRequestBuilder();
        ClientResponse clientResponse = null;
        String response = null;

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            build.header(entry.getKey(), entry.getValue());
        }

        if (mediaType != null) {
            build.type(mediaType);
        }

        try {
            clientResponse = build.post(ClientResponse.class, formData);
            if (clientResponse.getStatus() != 200 && clientResponse.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + clientResponse.getStatus());
            }
            response = clientResponse.getEntity(String.class);
            
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    public String ExecutePost(String url, HashMap<String, String> headers, String mediaType, String entity) {

        Client rsclient = this.getClient();
        WebResource webResource = rsclient.resource(url);
        Builder build = webResource.getRequestBuilder();
        ClientResponse clientResponse = null;
        String response = null;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            build.header(entry.getKey(), entry.getValue());
        }

        if (mediaType != null) {
            build.type(mediaType);
        }

        try {
            clientResponse = build.post(ClientResponse.class, entity);
            if (clientResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + clientResponse.getStatus());
            }
            response = clientResponse.getEntity(String.class);
            
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    public String ExecutePut(String url, HashMap<String, String> headers, String mediaType, String entity) {
        
        Client rsclient = this.getClient();
        Builder build = rsclient.resource(url).getRequestBuilder();
        ClientResponse clientResponse = null;
        String response = null;

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            build.header(entry.getKey(), entry.getValue());
        }

        if (mediaType != null) {
            build.type(mediaType);
        }

        try {
            clientResponse = build.put(ClientResponse.class, entity);
            if (clientResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + clientResponse.getStatus());
            }
            response = clientResponse.getEntity(String.class);
            
        } catch (Exception e) {
            throw e;
        }
        return response;
    }
}
