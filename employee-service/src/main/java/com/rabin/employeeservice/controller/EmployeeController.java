package com.rabin.employeeservice.controller;

import com.rabin.employeeservice.dto.EmployeeDto;
import com.rabin.employeeservice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeDto savingTheRecord(@RequestBody EmployeeDto employeeDto){
      return  employeeService.registeringTheRecord(employeeDto);
    }

    @GetMapping("/all")
    public List<EmployeeDto> getAllRecord(){
      return  employeeService.getAllListOfRecord();
    }

    @GetMapping("/{id}")
    public EmployeeDto gettingRecordById(@PathVariable("id") Long id){
       return employeeService.getRecordById(id);
    }

    @GetMapping("/department/{department}")
    public List<EmployeeDto> findByDepartment(@PathVariable("departmentId") Long departmentId){
      return  employeeService.findByDepartment(departmentId);

    }
}
