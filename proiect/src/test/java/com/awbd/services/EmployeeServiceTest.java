package com.awbd.services;

import com.awbd.model.Employee;
import com.awbd.repository.EmployeeRepository;
import com.awbd.service.EmployeeService;
import com.awbd.service.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void findEmployees(){
        List<Employee> employeesRet = new ArrayList<Employee>();
        Employee employee = new Employee();
        employeesRet.add(employee);


        when(employeeRepository.findAll()).thenReturn(employeesRet);
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(employees.size(), 1);
        verify(employeeRepository, times(1)).findAll();
    }
}
