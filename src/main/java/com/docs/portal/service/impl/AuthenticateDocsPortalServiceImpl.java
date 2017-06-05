/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import com.docs.portal.beans.authentication.AuthUser;
import com.docs.portal.dao.PortalUsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author mohchand
 */
@Service
public class AuthenticateDocsPortalServiceImpl implements UserDetailsService {

    @Autowired
    PortalUsersDAO portalUsersDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = portalUsersDAO.getUserDetailsForAuthentication(username);
        if (authUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
//            usersDAO.updateLastLoggedIn(username);
            return authUser;
        }
    }
}
