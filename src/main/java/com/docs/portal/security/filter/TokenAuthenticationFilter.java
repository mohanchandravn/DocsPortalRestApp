/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.security.filter;

import com.docs.portal.security.TokenBasedAuthentication;
import com.docs.portal.service.impl.AuthenticateDocsPortalServiceImpl;
import com.docs.portal.util.TokenHelper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author mohchand
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("${auth.header}")
    private String AUTH_HEADER;

    //    @Value("${jwt.cookie}")
    //    private String AUTH_COOKIE;
    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    AuthenticateDocsPortalServiceImpl authenticateDocsPortalServiceImpl;

    private String getToken(HttpServletRequest request) {
        /**
         * Getting the token from Cookie store
         */
        //        Cookie authCookie = getCookieValueByName( request, AUTH_COOKIE );
        //        if ( authCookie != null ) {
        //            return authCookie.getValue();
        //        }
        /**
         * Getting the token from Authentication header e.g Bearer your_token
         */
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request The HTTP request object.
     * @param name The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    protected Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i]
                    .getName()
                    .equals(name)) {
                return request.getCookies()[i];
            }
        }
        return null;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        logger.info("Checking the users token against user");
        String authToken = getToken(request);
        // get username from token
        String username = tokenHelper.getUsernameFromToken(authToken);
        if (username != null) {
            // get user
            UserDetails userDetails = authenticateDocsPortalServiceImpl.loadUserByUsername(username);
            // create authentication
            TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
            authentication.setToken(authToken);
            authentication.setAuthenticated(true);
            logger.info("User is valid for the token");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

}
