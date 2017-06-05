/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.exceptions;

/**
 *
 * @author mohchand
 */
import static java.lang.String.format;

public class FailedToLoginException extends RuntimeException {

    public FailedToLoginException(String username) {
        super(format("Failed to login with username %s", username));
    }
}
