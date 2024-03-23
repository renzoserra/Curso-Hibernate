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
     * Obtener un empleado por su id
     * @param id
     * @return empleado
     */
    Employee findById(Long id);

    /**
     * Obtener un listado de los empleados por su edad
     * @param age
     * @return lista de los empleados
     */
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
