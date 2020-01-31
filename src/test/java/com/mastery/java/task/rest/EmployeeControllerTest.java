package com.mastery.java.task.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private Employee employee;
    private List<Employee> employeeList;

    private final Long employeeId = 1L;
    private final Long departmentId = 1L;
    private final String employeeName = "EMPLOYEE_Name_TEST";
    private final String employeeLast = "EMPLOYEE_Last_Test";
    private final Gender gender = Gender.MALE;
    private final String jobTittle = "JOB_TITTLE";
    private final Date date = new Date();

    @Before
    public void init() {
        employee = Employee.builder()
                .employeeId(employeeId)
                .firstName(employeeName)
                .lastName(employeeLast)
                .gender(gender)
                .jobTittle(jobTittle)
                .dateOfBirth(date)
                .departmentId(departmentId)
                .build();

        employeeList = new ArrayList<>();
        employeeList.add(employee);
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].firstName").value(employeeName));
    }

    @Test
    public void getEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional
                .of(employee));
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", employeeId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(employeeId));
    }

    @Test
    public void createEmployeeTest() throws Exception {
        when(employeeService.createEmployee(employee)).thenReturn(Optional
                .of(employee));
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .content(asJsonString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender")
                        .value(gender.getGenderField().toUpperCase()));
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        employee.setFirstName(employeeName + employeeName);
        when(employeeService.updateEmployee(employee, employeeId))
                .thenReturn(Optional.of(employee));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/employees/{employeeId}", employeeId)
                .content(asJsonString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName")
                        .value(employeeName + employeeName));
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/{employeeId}", employeeId))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Employee employee) {
        try {
            return new ObjectMapper().writeValueAsString(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
