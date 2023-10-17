package com.rabin.employeeservice.controller;

import com.rabin.employeeservice.dto.EmployeeDto;
import com.rabin.employeeservice.service.EmployeeService;
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
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService mockEmployeeService;

    @Test
    void testSavingTheRecord() throws Exception {
        // Setup
        // Configure EmployeeService.registeringTheRecord(...).
        final EmployeeDto employeeDto = new EmployeeDto(0L, 0L, "name", 0, "position");
        when(mockEmployeeService.registeringTheRecord(new EmployeeDto(0L, 0L, "name", 0, "position")))
                .thenReturn(employeeDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/employee")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllRecord() throws Exception {
        // Setup
        // Configure EmployeeService.getAllListOfRecord(...).
        final List<EmployeeDto> employeeDtos = List.of(new EmployeeDto(0L, 0L, "name", 0, "position"));
        when(mockEmployeeService.getAllListOfRecord()).thenReturn(employeeDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllRecord_EmployeeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEmployeeService.getAllListOfRecord()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGettingRecordById() throws Exception {
        // Setup
        // Configure EmployeeService.getRecordById(...).
        final EmployeeDto employeeDto = new EmployeeDto(0L, 0L, "name", 0, "position");
        when(mockEmployeeService.getRecordById(0L)).thenReturn(employeeDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindByDepartment() throws Exception {
        // Setup
        // Configure EmployeeService.findByDepartment(...).
        final List<EmployeeDto> employeeDtos = List.of(new EmployeeDto(0L, 0L, "name", 0, "position"));
        when(mockEmployeeService.findByDepartment(0L)).thenReturn(employeeDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee/department/{department}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindByDepartment_EmployeeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEmployeeService.findByDepartment(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/employee/department/{department}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
