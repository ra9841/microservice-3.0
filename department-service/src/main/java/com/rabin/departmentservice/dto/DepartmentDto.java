package com.rabin.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentDto {
    private Long id;
    private String name;
    private List<EmployeeDto> employees;
}
