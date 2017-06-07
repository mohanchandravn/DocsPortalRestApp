/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.util;

/**
 *
 * @author mohchand
 */
public class SqlQueryConstantsUtil {

    public static final String SQL_GET_USER_FOR_AUTHENTICATION
            = "SELECT USER_ID, PASSWORD, FIRST_NAME, LAST_NAME, AUTHORITY, COMPANY_NAME FROM PORTAL_USERS WHERE USER_ID = ?";

    public static final String SQL_GET_USER
            = "SELECT USER_ID, PASSWORD, EMAIL, USER_ROLE, FIRST_NAME, LAST_NAME, ACTIVE, PWD_LAST_CHANGED, LAST_LOGGED_IN, COMPANY_NAME, PHONE FROM PORTAL_USERS WHERE USER_ID = ?";
    public static final String SQL_UPDATE_USER_LAST_LOGGED_IN
            = "UPDATE PORTAL_USERS SET LAST_LOGGED_IN = SYSDATE WHERE USER_ID = ?";

}
