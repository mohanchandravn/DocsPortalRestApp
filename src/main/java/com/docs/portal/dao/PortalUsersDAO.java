/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.dao;

import com.docs.portal.beans.PortalUser;
import com.docs.portal.beans.authentication.AuthUser;

/**
 *
 * @author mohchand
 */
public interface PortalUsersDAO {

    public PortalUser getUser(String userId);

    public void updateLastLoggedIn(String userId);

    public AuthUser getUserDetailsForAuthentication(String userId);
}
