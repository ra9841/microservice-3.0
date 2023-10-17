package com.rabin.departmentservice.controller;

import com.rabin.departmentservice.client.EmployeeClient;
import com.rabin.departmentservice.dto.DepartmentDto;
import com.rabin.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;



    @PostMapping
    public DepartmentDto savingTheRecord(@RequestBody DepartmentDto departmentDto){
      return  departmentService.registeringTheDepartment(departmentDto);
    }

    @GetMapping
    public List<DepartmentDto> gettingAllRecord(){
         return departmentService.getListOfRecord();
    }

    @GetMapping("/{id}")
    public DepartmentDto getRecordById(@PathVariable long id) throws Exception {
       return departmentService.getRecordById(id);
    }

    @GetMapping("/with-employees")
    public List<DepartmentDto> findAllWithEmployees(){
       return departmentService.findAllWithEmployee();
    }
}
