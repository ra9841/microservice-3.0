package com.rabin.departmentservice.controller;

import com.rabin.departmentservice.dto.DepartmentDto;
import com.rabin.departmentservice.dto.EmployeeDto;
import com.rabin.departmentservice.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService mockDepartmentService;

    @Test
    void testSavingTheRecord() throws Exception {
        // Setup
        // Configure DepartmentService.registeringTheDepartment(...).
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));
        final DepartmentDto departmentDto1 = new DepartmentDto();
        departmentDto1.setId(0L);
        departmentDto1.setName("name");
        final EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setId(0L);
        employeeDto1.setDepartmentId(0L);
        departmentDto1.setEmployees(List.of(employeeDto1));
        when(mockDepartmentService.registeringTheDepartment(departmentDto1)).thenReturn(departmentDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/department")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGettingAllRecord() throws Exception {
        // Setup
        // Configure DepartmentService.getListOfRecord(...).
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));
        final List<DepartmentDto> departmentDtos = List.of(departmentDto);
        when(mockDepartmentService.getListOfRecord()).thenReturn(departmentDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/department")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGettingAllRecord_DepartmentServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockDepartmentService.getListOfRecord()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/department")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetRecordById() throws Exception {
        // Setup
        // Configure DepartmentService.getRecordById(...).
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));
        when(mockDepartmentService.getRecordById(0L)).thenReturn(departmentDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/department/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetRecordById_DepartmentServiceThrowsException() throws Exception {
        // Setup
        when(mockDepartmentService.getRecordById(0L)).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/department/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindAllWithEmployees() throws Exception {
        // Setup
        // Configure DepartmentService.findAllWithEmployee(...).
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));
        final List<DepartmentDto> departmentDtos = List.of(departmentDto);
        when(mockDepartmentService.findAllWithEmployee()).thenReturn(departmentDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/department/with-employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindAllWithEmployees_DepartmentServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockDepartmentService.findAllWithEmployee()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/department/with-employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
