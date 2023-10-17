package com.rabin.employeeservice.service;

import com.rabin.employeeservice.dto.EmployeeDto;
import com.rabin.employeeservice.entity.Employee;
import com.rabin.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository mockEmployeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImplUnderTest;

    @Test
    void testRegisteringTheRecord() {
        // Setup
        final EmployeeDto employeeDto = new EmployeeDto(0L, 0L, "name", 0, "position");
        final EmployeeDto expectedResult = new EmployeeDto(0L, 0L, "name", 0, "position");

        // Configure EmployeeRepository.save(...).
        final Employee employee = new Employee(0L, 0L, "name", 0, "position");
        when(mockEmployeeRepository.save(new Employee(0L, 0L, "name", 0, "position"))).thenReturn(employee);

        // Run the test
        final EmployeeDto result = employeeServiceImplUnderTest.registeringTheRecord(employeeDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllListOfRecord() {
        // Setup
        final List<EmployeeDto> expectedResult = List.of(new EmployeeDto(0L, 0L, "name", 0, "position"));

        // Configure EmployeeRepository.findAll(...).
        final List<Employee> employees = List.of(new Employee(0L, 0L, "name", 0, "position"));
        when(mockEmployeeRepository.findAll()).thenReturn(employees);

        // Run the test
        final List<EmployeeDto> result = employeeServiceImplUnderTest.getAllListOfRecord();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllListOfRecord_EmployeeRepositoryReturnsNoItems() {
        // Setup
        when(mockEmployeeRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<EmployeeDto> result = employeeServiceImplUnderTest.getAllListOfRecord();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetRecordById() {
        // Setup
        final EmployeeDto expectedResult = new EmployeeDto(0L, 0L, "name", 0, "position");

        // Configure EmployeeRepository.findById(...).
        final Optional<Employee> employee = Optional.of(new Employee(0L, 0L, "name", 0, "position"));
        when(mockEmployeeRepository.findById(0L)).thenReturn(employee);

        // Run the test
        final EmployeeDto result = employeeServiceImplUnderTest.getRecordById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetRecordById_EmployeeRepositoryReturnsAbsent() {
        // Setup
        when(mockEmployeeRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> employeeServiceImplUnderTest.getRecordById(0L))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testFindByDepartment() {
        // Setup
        final List<EmployeeDto> expectedResult = List.of(new EmployeeDto(0L, 0L, "name", 0, "position"));

        // Configure EmployeeRepository.findByDepartmentId(...).
        final List<Employee> employees = List.of(new Employee(0L, 0L, "name", 0, "position"));
        when(mockEmployeeRepository.findByDepartmentId(0L)).thenReturn(employees);

        // Run the test
        final List<EmployeeDto> result = employeeServiceImplUnderTest.findByDepartment(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByDepartment_EmployeeRepositoryReturnsNoItems() {
        // Setup
        when(mockEmployeeRepository.findByDepartmentId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<EmployeeDto> result = employeeServiceImplUnderTest.findByDepartment(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
