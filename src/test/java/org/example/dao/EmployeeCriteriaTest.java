package org.example.dao;

import org.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeeCriteriaTest  {
    EmployeeDao dao;
    @BeforeEach
    public void setUp(){
        dao = new EmployeeDaoImpl();
    }


    @Test
    public void findAllCriteria(){
        List<Employee>  employees = dao.findAllCriteria();
        System.out.println(employees);
    }
    @Test
    public void findByIdCriteria(){
        Employee  employee = dao.findByIdCriteria(1L);
        System.out.println(employee);
    }
    @Test
    public void findByLastNameLikeCriteria(){
        List<Employee>  employees = dao.findByLastNameLikeCriteria("ez");
        System.out.println(employees);
    }
    @Test
    public void findByAgeGreaterCriteria(){
        List<Employee>  employees = dao.findByAgeGreaterCriteria(24);
        System.out.println(employees);
    }
    @Test
    public void findByAgeBetweenCriteria(){
        List<Employee>  employees = dao.findByAgeBetweenCriteria(19,40);
        System.out.println(employees);
    }

}