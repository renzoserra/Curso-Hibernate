package org.example.entities;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeTest {

    @Test
    public void createTableTest(){

        Employee employee1 = new Employee(null,
                "empleado1",
                "gonzalez",
                "empleado1@correo.com",
                18,
                40000d,
                false,
                LocalDate.of(1990,1,1),
                LocalDateTime.now());
        Employee employee2 = new Employee(null,
                "empleado2",
                "guerrero",
                "empleado2@correo.com",
                20,
                60000d,
                true,
                LocalDate.of(1988,1,1),
                LocalDateTime.now());

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
}
