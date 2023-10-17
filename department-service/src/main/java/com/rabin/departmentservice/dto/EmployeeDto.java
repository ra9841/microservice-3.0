package com.rabin.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {
    private Long id;
    private Long departmentId;
    private String name;
    private int age;
    private String position;

}
