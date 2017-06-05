/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.dao.impl;

import com.docs.portal.beans.PortalUser;
import com.docs.portal.beans.authentication.AuthUser;
import com.docs.portal.dao.PortalUsersDAO;
import com.docs.portal.util.SqlQueryConstantsUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mohchand
 */
@Repository
public class PortalUsersDAOImpl implements PortalUsersDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public AuthUser getUserDetailsForAuthentication(String userId) {
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<AuthUser> users
                = jdbcTemplate.query(SqlQueryConstantsUtil.SQL_GET_USER_FOR_AUTHENTICATION, new Object[]{userId},
                        new BeanPropertyRowMapper(AuthUser.class));
        return !users.isEmpty() ? users.get(0) : null;
    }

    
        @Override
    public PortalUser getUser(String userId) {
 
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<PortalUser> users =
            jdbcTemplate.query(SqlQueryConstantsUtil.SQL_GET_USER, new Object[] { userId },
                               new BeanPropertyRowMapper(PortalUser.class));

        return !users.isEmpty() ? users.get(0) : null;
    }

    @Override
    public void updateLastLoggedIn(String userId) {
        jdbcTemplate.update(SqlQueryConstantsUtil.SQL_UPDATE_USER_LAST_LOGGED_IN, new Object[] { userId });
    }
}
