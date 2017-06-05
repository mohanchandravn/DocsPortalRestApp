/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.security;

import com.docs.portal.exceptions.UserNotFoundException;
import com.docs.portal.util.EncryptionUtil;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author mohchand
 */
public class LoginPasswwordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String hashed;
        try {
            hashed = EncryptionUtil.encryptString(rawPassword.toString());
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }

        //        String hashed = BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
        return hashed;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return EncryptionUtil.encryptString(rawPassword.toString()).equals(encodedPassword);
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
        //        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}
