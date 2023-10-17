package com.rabin.departmentservice.service;


import com.rabin.departmentservice.client.EmployeeClient;
import com.rabin.departmentservice.dto.DepartmentDto;
import com.rabin.departmentservice.entity.Department;
import com.rabin.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;

    @Override
    public DepartmentDto registeringTheDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDto, department);
        Department department1 = departmentRepository.save(department);
        DepartmentDto departmentDto1 = new DepartmentDto();
        BeanUtils.copyProperties(department1, departmentDto1);
        return departmentDto1;
    }

    @Override
    public List<DepartmentDto> getListOfRecord() {
        return departmentRepository.findAll().stream().map(department -> {
            DepartmentDto departmentDto = new DepartmentDto();
            BeanUtils.copyProperties(department, departmentDto);
            return departmentDto;
        }).collect(Collectors.toList());

    }

    @Override
    public DepartmentDto getRecordById(long id) throws Exception {
        Optional<Department> existId = departmentRepository.findById(id);
        if (existId.isPresent()) {
            Department department = existId.get();
            DepartmentDto departmentDto = new DepartmentDto();
            BeanUtils.copyProperties(department, departmentDto);
            return departmentDto;
        } else {
            throw new Exception("id not found.....!");
        }

    }

    @Override
    public List<DepartmentDto> findAllWithEmployee() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .peek(department -> department.setEmployees(employeeClient.findByDepartment(department.getId())))
                .map(department -> {
                    DepartmentDto departmentDto = new DepartmentDto();
                    BeanUtils.copyProperties(department, departmentDto);
                    return departmentDto;
                })
                .collect(Collectors.toList());
    }

}
