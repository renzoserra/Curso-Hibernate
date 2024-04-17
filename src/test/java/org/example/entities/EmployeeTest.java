package org.example.entities;

import org.example.dao.EmployeeDao;
import org.example.dao.EmployeeDaoImpl;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeTest {

    @Test
    public void createTableTest() {

        Employee employee1 = new Employee(null,
                "empleado1",
                "gonzalez",
                "empleado1@correo.com",
                18,
                40000d,
                false,
                LocalDate.of(1990, 1, 1),
                LocalDateTime.now()
        );

        Employee employee2 = new Employee(null,
                "empleado2",
                "guerrero",
                "empleado2@correo.com",
                20,
                60000d,
                true,
                LocalDate.of(1988, 1, 1),
                LocalDateTime.now()
        );

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(employee1);
        session.save(employee2);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        HibernateUtil.shutdown();
    }

    @Test
    public void nickNamesTest() {

        Employee employee = new Employee(null,
                "Emp JUnit Nicknames",
                "gonzalez",
                "empleado1@correo.com",
                18,
                40000d,
                false,
                LocalDate.of(1990, 1, 1),
                LocalDateTime.now()
        );

        // Opcion 1
        List<String> nickNames = new ArrayList<>();
//        nickNames.add("nickname1");
//        nickNames.add("nickname2");
//        nickNames.add("nickname3");
//        employee.setNicknames(nickNames);


        //Opcion 2
        employee.getNicknames().add("nickname1");
        employee.getNicknames().add("nickname2");
        employee.getNicknames().add("nickname3");
        employee.getPostalCodes().add(4013);
        employee.getPostalCodes().add(2100);
        employee.getPostalCodes().add(2300);
        employee.getCreditCards().add("1234-1234-1234-1234");
        employee.getCreditCards().add("1234-1234-1234-4567");
        employee.getCreditCards().add("1234-1234-1234-6789");
        employee.getPhones().put("12345", "Claro");
        employee.getPhones().put("45678", "Wom");
        employee.getPhones().put("98765", "Movistar");
        employee.setCategory(EmployeeCategory.JUNIOR);

        for (Map.Entry<String, String> phones : employee.getPhones().entrySet()) {
            System.out.print("Company: " + phones.getValue());
            System.out.println(" Number: " + phones.getKey());
        }

        EmployeeDao dao = new EmployeeDaoImpl();
        dao.create(employee);

    }





}
