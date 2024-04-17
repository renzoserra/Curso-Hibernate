package org.example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//Representa la tabla "ob_employees" en la base de datos


@Entity
@Table(name = "ob_employees") // opcional
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    private Integer age;

    private Double salary;

    private Boolean married;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @ElementCollection
    private List<String> nicknames = new ArrayList<>();

    @ElementCollection
    private List<Integer> postalCodes = new ArrayList<>();

    @ElementCollection
    private Set<String> creditCards = new HashSet<>();

    @ElementCollection
    private Map<String, String> phones = new HashMap<>();


    // Por defecto se almancena en bd por Ordinal (númerico)
    // Con EnumType se almacena en bd por String tal cual como fue creado en la clase
    @Enumerated(EnumType.STRING)
    EmployeeCategory category;



    public Employee(Long id, String firstName, String lastName, String email, Integer age, Double salary, Boolean married, LocalDate birthDate, LocalDateTime registerDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.married = married;
        this.birthDate = birthDate;
        this.registerDate = registerDate;
    }
}
