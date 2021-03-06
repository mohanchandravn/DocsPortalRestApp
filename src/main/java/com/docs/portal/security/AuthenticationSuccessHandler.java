/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.security;

/**
 *
 * @author mohchand
 */
import com.docs.portal.beans.authentication.AuthUser;
import com.docs.portal.beans.authentication.AuthUserTokenState;
import com.docs.portal.service.LoginService;
import com.docs.portal.util.TokenHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private LoginService loginService;

    @Value("${usercookie.expiresIn}")
    private int EXPIRES_IN;

    @Value("${usercookie.name}")
    private String USER_COOKIE;

    @Value("${forbidden.errorMessage}")
    private String FORBIDDEN_MESSAGE;

    @Value("${auth.portalType}")
    private String PORTAL_TYPE;

    @Value("${auth.userType}")
    private String USER_TYPE;

    @Value("${auth.adminType}")
    private String ADMIN_TYPE;

    @Value("${auth.cscType}")
    private String CSC_TYPE;

    @Autowired
    TokenHelper tokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("Authentication success, generating the token");
        clearAuthenticationAttributes(request);
        AuthUser user = (AuthUser) authentication.getPrincipal();
//        String portalType = request.getHeader(PORTAL_TYPE);
//        if (portalType.equalsIgnoreCase(ADMIN_TYPE) && (user.getAuthority().replace("ROLE_", "").equalsIgnoreCase(ADMIN_TYPE)
//                || user.getAuthority().replace("ROLE_", "").equalsIgnoreCase(CSC_TYPE))
//                || portalType.equalsIgnoreCase(USER_TYPE) && user.getAuthority().replace("ROLE_", "").equalsIgnoreCase(USER_TYPE)) {
            String jws = tokenHelper.generateToken(user.getUsername());
//            Cookie userCookie = new Cookie(USER_COOKIE, (user.getFirstName()));
//            userCookie.setPath("/");
//            userCookie.setMaxAge(EXPIRES_IN);
//            response.addCookie(userCookie);
            AuthUserTokenState userTokenState = null;
//            if (portalType.equalsIgnoreCase(USER_TYPE)) {
                userTokenState = loginService.getPortalUserDetails(user.getUserId(), jws, EXPIRES_IN, user.getAuthority().replace("ROLE_", ""));
//            } else {
//                userTokenState = loginService.getAdminUserDetails(user.getUserId(), jws, EXPIRES_IN, user.getAuthority().replace("ROLE_", "").equalsIgnoreCase(ADMIN_TYPE) ? ADMIN_TYPE : CSC_TYPE);
//            }
            ObjectMapper mapper = new ObjectMapper();
            String jwtResponse = mapper.writeValueAsString(userTokenState);
            response.setContentType("application/json");
            response.getWriter().write(jwtResponse);
//        } else {
//            response.setStatus(401);
//            response.getWriter().write(FORBIDDEN_MESSAGE);
//        }
    }
}
