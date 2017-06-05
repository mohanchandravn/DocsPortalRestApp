/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal.dao;

import com.docs.portal.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *
 * @author mohchand
 */
@Repository
public class EmployeeTemplateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> getAllEmployees() {
        String queryStr = "SELECT ID, FIRSTNAME, LASTNAME, EMAIL, PHONE, BIRTHDATE, TITLE, DEPARTMENT FROM EMPLOYEE";

        List<Employee> resultList = (List<Employee>) jdbcTemplate.query(queryStr,
                (rs, rowNum) -> new Employee(rs.getInt("ID"),
                        rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getString("BIRTHDATE"), rs.getString("TITLE"), rs.getString("DEPARTMENT")));
        return resultList;
    }

}
