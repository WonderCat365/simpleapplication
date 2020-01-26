package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDao employeeDao;

    private Employee employee;
    private List<Employee> employees;
    private Long employeeId = 1L;
    private Date date = new Date();

    private final int SUCCESS_JDBC_TEMPLATE_RESPONSE = 1;
    private final int COUNT_OF_EMPLOYEES = 3;

    @Before
    public void init() {

        employees = new ArrayList<>();
        employee = Employee.builder()
                .firstName("EMPLOYEE_NAME")
                .lastName("EMPLOYEE_LAST_NAME")
                .departmentId(1L)
                .jobTittle("JOB_TITTLE")
                .gender(Gender.MALE)
                .dateOfBirth(date)
                .build();

        employees.add(employee);
    }

    @Test
    public void addEmployeeTest() {

        when(employeeDao.save(employee)).thenReturn(SUCCESS_JDBC_TEMPLATE_RESPONSE);

        employeeService.createEmployee(employee);
        verify(employeeDao).save(employee);
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void getAllEmployeesTest() {
        when(employeeDao.findAll()).thenReturn(new ArrayList<Employee>());

        List<Employee> actualList = employeeService.getAllEmployees();
        verify(employeeDao).findAll();
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void updateEmployeeTest() {
        when(employeeDao.update(employee, employeeId)).thenReturn(SUCCESS_JDBC_TEMPLATE_RESPONSE);

        Employee employeeTest = employeeService.updateEmployee(employee, employeeId).get();
        verify(employeeDao).update(employee, employeeId);
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void deleteEmployeeTest(){
        employeeService.deleteEmployee(employeeId);
        verify(employeeDao, times(1)).deleteById(employeeId);
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void getEmployeeByIdTest(){
        when(employeeDao.findById(employeeId)).thenReturn(Optional.of(employee));

        employeeService.getEmployeeById(employeeId);
        verify(employeeDao).findById(employeeId);
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void getCountTest(){
        when(employeeDao.count()).thenReturn(COUNT_OF_EMPLOYEES);

        employeeService.getCountOfEmployee();
        verify(employeeDao).count();
        verifyNoMoreInteractions(employeeDao);
    }
}
