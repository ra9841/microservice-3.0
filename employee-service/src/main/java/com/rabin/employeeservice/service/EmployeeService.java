package com.rabin.employeeservice.service;

import com.rabin.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto registeringTheRecord(EmployeeDto employeeDto);

    List<EmployeeDto> getAllListOfRecord();

    EmployeeDto getRecordById(Long id);

    List<EmployeeDto> findByDepartment(Long departmentId);
}
