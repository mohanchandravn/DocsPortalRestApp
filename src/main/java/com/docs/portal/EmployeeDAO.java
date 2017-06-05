package com.docs.portal;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohchand
 */
public interface EmployeeDAO {
    public List<Employee> getAllEmployees();
    public Employee getEmployee(long id);
    public List<Employee> getByLastName(String name);
    public List<Employee> getByTitle(String title);
    public List<Employee> getByDepartment(String department);
    public boolean add(Employee employee);  // False equals fail
    public boolean update(long id, Employee employee); // False equals fail
    public boolean delete(long id); // False equals fail
}
