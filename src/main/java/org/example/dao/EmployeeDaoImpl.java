package org.example.dao;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

    public List<Employee> findAllCriteria() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //1. Criteria
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        criteriaQuery.select(criteriaQuery.from(Employee.class));

        //2. Query
        List<Employee> employeeList = session.createQuery(criteriaQuery).list();

        session.close();

        return employeeList;
    }

    public Employee findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee = session.find(Employee.class, id);
        session.close();
        return employee;
    }

    public Employee findByIdCriteria(Long id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        //1. Criteria
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        //Obtengo la raiz. Es una simulación en sql de hacer un select from employee
        Root <Employee> root = criteriaQuery.from(Employee.class);

        // Construir el predicado que va a ser como el filtro de la query
        Predicate filter = criteriaBuilder.equal(root.get("id"),id);

        //armardo completo de la query

        criteriaQuery.select(root).where(filter);

        //2. Query
        Employee employee = session.createQuery(criteriaQuery).getSingleResult();

        session.close();

        return employee;
    }

    public List<Employee> findByLastNameLikeCriteria(String lastName) {
        // equivalente a contains
        // SELECT * FROM ob_employees WHERE lastName like '%ence%'

        Session session = HibernateUtil.getSessionFactory().openSession();

        // 1. criteria
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);

        // Preparación del predicate para refinar la busqueda
        Predicate filter = builder.like(root.get("lastName"), "%" + lastName + "%");

        criteria.select(root).where(filter);

        // 2. query
        List<Employee> employees = session.createQuery(criteria).list();

        session.close();
        return employees;
    }

    public List<Employee> findByAgeGreaterCriteria(Integer age) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // 1. criteria
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);

        // Preparación del predicate para refinar la busqueda
        Predicate filter = builder.gt(root.get("age"), age);

        criteria.select(root).where(filter);

        // 2. query
        List<Employee> employees = session.createQuery(criteria).list();

        session.close();
        return employees;
    }

    public List<Employee> findByAgeBetweenCriteria(Integer min, Integer max) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // 1. criteria
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);

        // Preparación del predicate para refinar la busqueda
        Predicate filter = builder.between(root.get("age"), min,max);

        criteria.select(root).where(filter);

        // 2. query
        List<Employee> employees = session.createQuery(criteria).list();

        session.close();
        return employees;
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
