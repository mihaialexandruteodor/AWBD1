package com.awbd.controllers;

import com.awbd.controller.EmployeeController;
import com.awbd.model.Employee;
import com.awbd.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @Mock
    Model model;

    @Mock
    EmployeeService employeeService;

    EmployeeController employeeController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        employeeController = new EmployeeController(employeeService);
    }

    @Captor
    ArgumentCaptor<Employee> argumentCaptor;


    @Test
    public void showById() {
        Long id = 1l;
        Employee employeeTest = new Employee();
        employeeTest.setId(id);

        when(employeeService.getEmployeeById(id)).thenReturn(employeeTest);

        String viewName = employeeController.showById(id.toString(), model);
        Assert.assertEquals("update_employee", viewName);
        verify(employeeService, times(1)).getEmployeeById(id);

        //ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(model, times(1))
                .addAttribute(eq("employee"), argumentCaptor.capture() );

        Employee employeeArg = argumentCaptor.getValue();
        Assert.assertEquals(employeeArg.getId(), employeeTest.getId() );

    }
}
