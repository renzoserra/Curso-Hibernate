package org.example.dao;

import jakarta.persistence.PersistenceException;
import org.example.entities.Employee;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements  EmployeeDao{

    public List<Employee> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // HQL query. En vez de colocar el nombre de la tabla en la query se coloca el nombre de la clase java
        List<Employee> employeeList = session.createQuery("from Employee", Employee.class).list();
        session.close();

        return employeeList;
    }

    public Employee findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee = session.find(Employee.class, id);
        session.close();
        return employee;
    }

    public List<Employee> findByAge(Integer age) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Employee> query = session.createQuery("from Employee where age = :age", Employee.class);
        query.setParameter("age",age);
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }

    public Employee create(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return employee;
    }

    public Employee update(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return employee;
    }

    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Employee employee = this.findById(id);
            session.delete(employee);

            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally{
            session.close();
        }
        return true;
    }
}
