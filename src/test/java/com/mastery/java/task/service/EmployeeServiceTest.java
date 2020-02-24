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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

  @Mock private EmployeeDao employeeDao;
  @InjectMocks private EmployeeService employeeService;

  private final Integer EMPLOYEE_ID = 4334;
  private final Long DEPARTMENT_ID = 32L;
  private final String EMPLOYEE_FIRST_NAME = "EMPLOYEE NAME",
      EMPLOYEE_LAST_NAME = "EMPLOYEE LAST NAME",
      JOB_TITTLE = "JOB TITTLE";
  private final Gender GENDER = Gender.FEMALE;
  private final Date DATE_OF_BIRTH = new Date();
  private Employee employee;
  private List<Employee> employeeList = new ArrayList<>();

  @Before
  public void init() {
    employee =
        Employee.builder()
            .employeeId(EMPLOYEE_ID)
            .firstName(EMPLOYEE_FIRST_NAME)
            .lastName(EMPLOYEE_LAST_NAME)
            .departmentId(DEPARTMENT_ID)
            .jobTittle(JOB_TITTLE)
            .gender(GENDER)
            .dateOfBirth(DATE_OF_BIRTH)
            .build();
    employeeList.add(employee);
  }

  @Test
  public void getAllEmployeesTest() {
    when(employeeDao.getAllEmployees()).thenReturn(employeeList);
    assertEquals(employeeService.getAllEmployees(), employeeList);
    verify(employeeDao, times(1)).getAllEmployees();
    verifyNoMoreInteractions(employeeDao);
  }

  @Test
  public void createEmployeeTest() {
    when(employeeDao.createEmployee(employee)).thenReturn(employee);
    assertEquals(employeeService.createEmployee(employee), employee);
    verify(employeeDao, times(1)).createEmployee(any());
    verifyNoMoreInteractions(employeeDao);
  }

  @Test
  public void getEmployeeByIdTest() {
    when(employeeDao.getEmployeeById(EMPLOYEE_ID)).thenReturn(Optional.of(employee));
    assertEquals(employeeService.getEmployeeById(EMPLOYEE_ID).get(), employee);
    verify(employeeDao, times(1)).getEmployeeById(EMPLOYEE_ID);
    verifyNoMoreInteractions(employeeDao);
  }

  @Test
  public void getEmployeeByIdNegativeTest() {
    when(employeeDao.getEmployeeById(EMPLOYEE_ID + 1)).thenReturn(Optional.empty());
    assertNotEquals(employeeService.getEmployeeById(EMPLOYEE_ID + 1), employee);
    verify(employeeDao, times(1)).getEmployeeById(EMPLOYEE_ID + 1);
    verifyNoMoreInteractions(employeeDao);
  }
}
