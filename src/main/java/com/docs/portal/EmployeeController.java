/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docs.portal;

/**
 *
 * @author mohchand
 */
import com.docs.portal.beans.authentication.AuthUser;
import com.docs.portal.dao.EmployeeTemplateDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeDbDAO edao;

    @Autowired
    EmployeeTemplateDao etDao;

    // Get all employees
    @RequestMapping(method = RequestMethod.GET)
    public Employee[] getAll(Authentication authentication) {
        AuthUser user = (AuthUser) authentication.getPrincipal();
        logger.info("For testing Company name of logged in user : " + user.getCompanyName());
        return etDao.getAllEmployees().toArray(new Employee[0]);
    }

    // Get an employee
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity get(@PathVariable long id) {

        Employee match = null;
        match = edao.getEmployee(id);

        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get employees by lastName
    @RequestMapping(method = RequestMethod.GET, value = "/lastname/{name}")
    public ResponseEntity getByLastName(@PathVariable String name) {

        List matchList = edao.getByLastName(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Employee[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // Get employee by title
    @RequestMapping(method = RequestMethod.GET, value = "/title/{name}")
    public ResponseEntity getByTitle(@PathVariable String name) {

        List matchList = edao.getByTitle(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Employee[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get employee by dept
    @RequestMapping(method = RequestMethod.GET, value = "/department/{name}")
    public ResponseEntity getByDept(@PathVariable String name) {

        List matchList = edao.getByDepartment(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Employee[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Add an employee
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Employee employee) {
        if (edao.add(employee)) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an employee
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Employee employee) {

        if (edao.update(id, employee)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a employee
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable long id) {

        boolean result = edao.delete(id);

        if (result) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
