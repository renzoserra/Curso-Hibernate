package org.example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String firtsName;

    @Column(unique = true)
    private String email;

    private Integer age;

    private Double salary;

    private Boolean married;

    private LocalDate birthDate;

    private LocalDateTime registerDate;
}
