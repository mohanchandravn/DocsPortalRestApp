/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.service;

import com.docs.portal.beans.PortalUser;
import com.docs.portal.beans.authentication.AuthUserTokenState;

/**
 *
 * @author mohchand
 */
public interface LoginService {

    public PortalUser getUserDetails(String userName);

    public AuthUserTokenState getPortalUserDetails(String userName, String accessToken, long expiresIn);

    public AuthUserTokenState getAdminUserDetails(String userName, String accessToken, long expiresIn, String portalRole);
}
