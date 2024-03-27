package org.example.dao;

import org.example.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EmployeeCRUDTest {
    EmployeeDao dao;
    @BeforeEach
    public void setUp() throws Exception {
        dao = new EmployeeDaoImpl();

    }

    @Test
    public void testFindAll() {

        List<Employee> employees =  dao.findAll();
        System.out.println(employees);
    }
    @Test
    public void testFindById() {
        Employee employee1 = dao.findById(1L);
        Employee employee2 = dao.findById(2L);
        Employee employee6 = dao.findById(6L);
    }
    @Test
    public void testFindByAge() {
        List<Employee> employees35 = dao.findByAge(35);
        List<Employee> employees20 = dao.findByAge(20);
        List<Employee> employees50 = dao.findByAge(50);
    }
    @Test
    public void testCreate() {
        Employee employee = new Employee(null,"Pruebas Junit", "Test", "test@gmail.com",18,2000d,false, LocalDate.now(), LocalDateTime.now());
        dao.create(employee);
        System.out.println(employee);
    }
    @Test
    public void testUpdate() {
        Employee employee1 = new Employee(1L,
                "empleado1 editado",
                "gonzalez",
                "empleado1@correo.com",
                18,
                40000d,
                false,
                LocalDate.of(1990,1,1),
                LocalDateTime.now());
        Employee employee = dao.update(employee1);
        System.out.println(employee);
    }
    @Test
    public void testDeleteById() {
        boolean result = dao.deleteById(1L);
        System.out.println(result);

    }
}