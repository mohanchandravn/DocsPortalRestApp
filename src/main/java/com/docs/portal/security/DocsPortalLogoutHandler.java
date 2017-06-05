/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.security;

import com.docs.portal.util.TokenHelper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 *
 * @author mohchand
 */
public class DocsPortalLogoutHandler implements LogoutHandler {

    @Value("${usercookie.name}")
    private String USER_COOKIE;

    @Value("${auth.header}")
    private String AUTH_HEADER;

    @Value("${auth.portalType}")
    private String PORTAL_TYPE;

    @Value("${auth.userType}")
    private String USER_TYPE;

    @Value("${auth.adminType}")
    private String ADMIN_TYPE;

    @Autowired
    TokenHelper tokenHelper;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader(AUTH_HEADER);
        String portalType = request.getHeader(PORTAL_TYPE);
        if (portalType.equalsIgnoreCase(USER_TYPE) && null != authHeader) {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }
            String username = tokenHelper.getUsernameFromToken(authHeader);
        }

        Cookie userCookie = new Cookie(USER_COOKIE, null);
        userCookie.setPath("/");
        userCookie.setMaxAge(0);

        //            response.addCookie(authCookie);
        response.addCookie(userCookie);
    }

}
