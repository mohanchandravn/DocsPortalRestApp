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

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super(format("User with username %s does not exist", username));
    }

}
