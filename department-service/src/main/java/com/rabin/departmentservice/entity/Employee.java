package com.rabin.departmentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="employee_tbl")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long departmentId;
    private String name;
    private int age;
    private String position;
    @ManyToOne // Create a field to represent the relationship with Department
    @JoinColumn(name = "department") // Specify the foreign key column
    private Department department;

}