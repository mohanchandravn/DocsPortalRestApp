/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.beans.authentication;

/**
 *
 * @author mohchand
 */
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthUserTokenState {

    private String access_token;
    private long expires_in;
    private String userId;
    private String email;
    private String userRole;
    private String firstName;
    private String lastName;
    private String phone;
    @JsonIgnore
    private boolean firstLogin;
    @JsonIgnore
    private boolean changePwd;
    @JsonIgnore
    private String portalRole;
    private String companyName;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

 
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setChangePwd(boolean changePwd) {
        this.changePwd = changePwd;
    }

    public boolean isChangePwd() {
        return changePwd;
    }

    public void setPortalRole(String portalRole) {
        this.portalRole = portalRole;
    }

    public String getPortalRole() {
        return portalRole;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
