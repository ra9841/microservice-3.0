package com.rabin.departmentservice.service;

import com.rabin.departmentservice.client.EmployeeClient;
import com.rabin.departmentservice.dto.DepartmentDto;
import com.rabin.departmentservice.dto.EmployeeDto;
import com.rabin.departmentservice.entity.Department;
import com.rabin.departmentservice.entity.Employee;
import com.rabin.departmentservice.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository mockDepartmentRepository;
    @Mock
    private EmployeeClient mockEmployeeClient;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImplUnderTest;

    @Test
    void testRegisteringTheDepartment() {
        // Setup
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));

        final DepartmentDto expectedResult = new DepartmentDto();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        final EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setId(0L);
        employeeDto1.setDepartmentId(0L);
        expectedResult.setEmployees(List.of(employeeDto1));

        // Configure DepartmentRepository.save(...).
        final Department department = new Department();
        department.setId(0L);
        department.setName("name");
        final Employee employee = new Employee();
        employee.setId(0L);
        employee.setDepartmentId(0L);
        department.setEmployees(List.of(employee));
        final Department entity = new Department();
        entity.setId(0L);
        entity.setName("name");
        final Employee employee1 = new Employee();
        employee1.setId(0L);
        employee1.setDepartmentId(0L);
        entity.setEmployees(List.of(employee1));
        when(mockDepartmentRepository.save(entity)).thenReturn(department);

        // Run the test
        final DepartmentDto result = departmentServiceImplUnderTest.registeringTheDepartment(departmentDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetListOfRecord() {
        // Setup
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));
        final List<DepartmentDto> expectedResult = List.of(departmentDto);

        // Configure DepartmentRepository.findAll(...).
        final Department department = new Department();
        department.setId(0L);
        department.setName("name");
        final Employee employee = new Employee();
        employee.setId(0L);
        employee.setDepartmentId(0L);
        department.setEmployees(List.of(employee));
        final List<Department> departments = List.of(department);
        when(mockDepartmentRepository.findAll()).thenReturn(departments);

        // Run the test
        final List<DepartmentDto> result = departmentServiceImplUnderTest.getListOfRecord();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetListOfRecord_DepartmentRepositoryReturnsNoItems() {
        // Setup
        when(mockDepartmentRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<DepartmentDto> result = departmentServiceImplUnderTest.getListOfRecord();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetRecordById() throws Exception {
        // Setup
        final DepartmentDto expectedResult = new DepartmentDto();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        expectedResult.setEmployees(List.of(employeeDto));

        // Configure DepartmentRepository.findById(...).
        final Department department1 = new Department();
        department1.setId(0L);
        department1.setName("name");
        final Employee employee = new Employee();
        employee.setId(0L);
        employee.setDepartmentId(0L);
        department1.setEmployees(List.of(employee));
        final Optional<Department> department = Optional.of(department1);
        when(mockDepartmentRepository.findById(0L)).thenReturn(department);

        // Run the test
        final DepartmentDto result = departmentServiceImplUnderTest.getRecordById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetRecordById_DepartmentRepositoryReturnsAbsent() {
        // Setup
        when(mockDepartmentRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> departmentServiceImplUnderTest.getRecordById(0L)).isInstanceOf(Exception.class);
    }

    @Test
    void testFindAllWithEmployee() {
        // Setup
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));
        final List<DepartmentDto> expectedResult = List.of(departmentDto);

        // Configure DepartmentRepository.findAll(...).
        final Department department = new Department();
        department.setId(0L);
        department.setName("name");
        final Employee employee = new Employee();
        employee.setId(0L);
        employee.setDepartmentId(0L);
        department.setEmployees(List.of(employee));
        final List<Department> departments = List.of(department);
        when(mockDepartmentRepository.findAll()).thenReturn(departments);

        // Configure EmployeeClient.findByDepartment(...).
        final Employee employee1 = new Employee();
        employee1.setId(0L);
        employee1.setDepartmentId(0L);
        employee1.setName("name");
        employee1.setAge(0);
        employee1.setPosition("position");
        final List<Employee> employees = List.of(employee1);
        when(mockEmployeeClient.findByDepartment(0L)).thenReturn(employees);

        // Run the test
        final List<DepartmentDto> result = departmentServiceImplUnderTest.findAllWithEmployee();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllWithEmployee_DepartmentRepositoryReturnsNoItems() {
        // Setup
        when(mockDepartmentRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<DepartmentDto> result = departmentServiceImplUnderTest.findAllWithEmployee();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindAllWithEmployee_EmployeeClientReturnsNoItems() {
        // Setup
        final DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(0L);
        departmentDto.setName("name");
        final EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(0L);
        employeeDto.setDepartmentId(0L);
        departmentDto.setEmployees(List.of(employeeDto));
        final List<DepartmentDto> expectedResult = List.of(departmentDto);

        // Configure DepartmentRepository.findAll(...).
        final Department department = new Department();
        department.setId(0L);
        department.setName("name");
        final Employee employee = new Employee();
        employee.setId(0L);
        employee.setDepartmentId(0L);
        department.setEmployees(List.of(employee));
        final List<Department> departments = List.of(department);
        when(mockDepartmentRepository.findAll()).thenReturn(departments);

        when(mockEmployeeClient.findByDepartment(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<DepartmentDto> result = departmentServiceImplUnderTest.findAllWithEmployee();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
