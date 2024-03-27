package org.example.dao;


import org.example.entities.Employee;

import java.util.List;

/**
 * Data access Object
 *
 * Metodos CRUD
 */
public interface EmployeeDao {

    /**
     * Obtener todos los empleados de la base de datos
     * @return lista de emepleados
     */
    List<Employee> findAll();
    /**
     * Obtener todos los empleados de la base de datos según el criterio
     * @return lista de emepleados
     */
    List<Employee> findAllCriteria();

    /**
     * Obtener un empleado por su id
     * @param id por metodo hql
     * @return empleado
     */
    Employee findById(Long id);

    /**
     * Obtener un empleado por su id
     * @param id por Criteria
     * @return empleado
     */
    Employee findByIdCriteria(Long id);

    /**
     * Obtener un listado de los empleados por su edad
     * @param lastName
     * @return lista de los empleados
     */
    List<Employee> findByLastNameLikeCriteria(String lastName);

    /**
     * Filtrar por edad. Mayor de determinado número
     * Criteria
     * @param age
     * @return
     */
    List<Employee> findByAgeGreaterCriteria(Integer age);

    /**
     * Filtrar por rango de edad entre dos números
     * Criteria
     * @param min
     * @param max
     * @return
     */
    List<Employee> findByAgeBetweenCriteria(Integer min, Integer max);
    List<Employee> findByAge(Integer age);

    /**
     * Crear un nuevo empleado en la BD
     * @param employee
     * @return devuelve el empleado
     */
    Employee create(Employee employee);

    /**
     * Actualiza la informacion del empleado en la BD
     * @param employee
     * @return devuelve el empleado actualizado
     */
    Employee update(Employee employee);

    /**
     * Borra el empleado en la BD
     * @param id
     * @return true si el empleado fue eliminado y false en caso contrario
     */
    boolean deleteById(Long id);


}
