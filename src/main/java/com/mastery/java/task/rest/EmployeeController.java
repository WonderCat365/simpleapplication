package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @PostMapping
  public Employee createEmployee(@RequestBody Employee employee) {
    return employeeService.createEmployee(employee);
  }

  @PutMapping
  public Employee updateEmployee(@RequestBody Employee employee) {
    return employeeService.updateEmployee(employee).get();
  }

  @DeleteMapping("/{employeeId}")
  public void deleteEmployee(@PathVariable(name = "employeeId") int employeeId) {

    employeeService.deleteEmployee(employeeId);
  }

  @GetMapping(value = "/{employeeId}")
  public Employee getEmployeeById(@PathVariable(value = "employeeId") int employeeId) {
    return employeeService.getEmployeeById(employeeId).get();
  }
}
