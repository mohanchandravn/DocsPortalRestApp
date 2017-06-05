/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohchand
 */
package com.docs.portal;

public class Employee {
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String birthDate;
  private String title;
  private String department;


  public Employee(){
      super();
      id = 0;
      firstName = "";
      lastName = "";
      email = "";
      phone = "";
      birthDate = "";
      title = "";
      department = "";
  }

  public Employee(long id, String firstName, String lastName, String email, String phone, String birthDate, String title, String department){
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.phone = phone;
      this.birthDate = birthDate;
      this.title = title;
      this.department = department;
  }

 
  @Override
  public String toString(){
    return "ID: " + getId()
        + " First Name: " + getFirstName()
        + " Last Name: " + getLastName()
        + " EMail: " + getEmail()
        + " Phone: " + getPhone()
        + " Birth Date: " + getBirthDate()
        + " Title: " + getTitle()
        + " Department: " + getDepartment();
  }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

}