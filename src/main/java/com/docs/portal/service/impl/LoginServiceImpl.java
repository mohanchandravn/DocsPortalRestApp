/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service.impl;

import com.docs.portal.beans.PortalUser;
import com.docs.portal.beans.authentication.AuthUserTokenState;
import com.docs.portal.dao.PortalUsersDAO;
import com.docs.portal.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author mohchand
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    PortalUsersDAO portalUsersDAO;

    @Override
    public PortalUser getUserDetails(String userName) {
        PortalUser dbUser = portalUsersDAO.getUser(userName);
        dbUser.setPassword(null);
        return dbUser;
    }

    @Override
    public AuthUserTokenState getPortalUserDetails(String userName, String accessToken, long expiresIn) {
        AuthUserTokenState userTokenState = new AuthUserTokenState();
        PortalUser userDetails = portalUsersDAO.getUser(userName);

        if (userDetails != null) {
            userTokenState.setAccess_token(accessToken);
            userTokenState.setExpires_in(expiresIn);
            userTokenState.setUserId(userName);
            userTokenState.setEmail(userDetails.getEmail());
            userTokenState.setUserRole(userDetails.getUserRole());
            userTokenState.setFirstName(userDetails.getFirstName());
            userTokenState.setLastName(userDetails.getLastName());
            userTokenState.setPhone(userDetails.getPhone());
            userTokenState.setCompanyName(userDetails.getCompanyName());
            if (StringUtils.isEmpty(userDetails.getLastLoggedIn())) {
                userTokenState.setFirstLogin(false);
            }
            if (StringUtils.isEmpty(userDetails.getPwdLastChanged())) {
                userTokenState.setChangePwd(true);
            }
        }

        portalUsersDAO.updateLastLoggedIn(userName);
        return userTokenState;
    }

    @Override
    public AuthUserTokenState getAdminUserDetails(String userName, String accessToken, long expiresIn, String portalRole) {
        AuthUserTokenState userTokenState = new AuthUserTokenState();
        PortalUser userDetails = portalUsersDAO.getUser(userName);
        if (userDetails != null) {
            userTokenState.setAccess_token(accessToken);
            userTokenState.setExpires_in(expiresIn);
            userTokenState.setUserId(userName);
            userTokenState.setEmail(userDetails.getEmail());
            userTokenState.setUserRole(userDetails.getUserRole());
            userTokenState.setFirstName(userDetails.getFirstName());
            userTokenState.setLastName(userDetails.getLastName());
            userTokenState.setPhone(userDetails.getPhone());
            userTokenState.setPortalRole(portalRole);
        }
        portalUsersDAO.updateLastLoggedIn(userName);
        return userTokenState;
    }
}
