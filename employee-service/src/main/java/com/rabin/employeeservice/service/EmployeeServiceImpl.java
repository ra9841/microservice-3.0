package com.rabin.employeeservice.service;

import com.rabin.employeeservice.dto.EmployeeDto;
import com.rabin.employeeservice.entity.Employee;
import com.rabin.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto registeringTheRecord(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        Employee employee1 = employeeRepository.save(employee);
        EmployeeDto employeeDto1 = new EmployeeDto();
        BeanUtils.copyProperties(employee1, employeeDto1);
        return employeeDto1;
    }

    @Override
    public List<EmployeeDto> getAllListOfRecord() {
        return employeeRepository.findAll().stream().map(employee -> {
            EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(employee, employeeDto);
            return employeeDto;
        }).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getRecordById(Long id) {
        Employee employee1 = employeeRepository.findById(id).stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow();
        log.info("id is {}", id);
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee1, employeeDto);
        return employeeDto;

    }

    @Override
    public List<EmployeeDto> findByDepartment(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream().map(employee -> {
            EmployeeDto employeeDto = new EmployeeDto();
            BeanUtils.copyProperties(employee, employeeDto);
            return employeeDto;
        }).collect(Collectors.toList());
    }
}
