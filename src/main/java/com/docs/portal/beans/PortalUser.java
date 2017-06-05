/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.beans;

import java.sql.Timestamp;

/**
 *
 * @author mohchand
 */
public class PortalUser {

    private String userId;
    private String password;
    private String email;
    private String userRole;
    private String firstName;
    private String lastName;
    private boolean active;
    private Timestamp pwdLastChanged;
    private Timestamp lastLoggedIn;
    private String registryId;
    private String phone;

    public PortalUser() {

    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setPwdLastChanged(Timestamp pwdLastChanged) {
        this.pwdLastChanged = pwdLastChanged;
    }

    public Timestamp getPwdLastChanged() {
        return pwdLastChanged;
    }

    public void setLastLoggedIn(Timestamp lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public Timestamp getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setRegistryId(String registryId) {
        this.registryId = registryId;
    }

    public String getRegistryId() {
        return registryId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
