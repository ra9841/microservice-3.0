package com.rabin.departmentservice.service;

import com.rabin.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto registeringTheDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getListOfRecord();

    DepartmentDto getRecordById(long id) throws Exception;

    List<DepartmentDto> findAllWithEmployee();
}
