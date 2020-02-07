package com.mastery.java.task.dao;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.interfases.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@PropertySource("classpath:sqlRequest.properties")
@Component
public class EmployeeDao {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee createEmployee(Employee employee) {
    return employeeRepository.saveAndFlush(employee);
  }

  public Optional<Employee> getEmployeeById(int employeeId) {
    return employeeRepository.findById(employeeId);
  }

  public Optional<Employee> updateEmployee(Employee employeeForUpdate) {

    return employeeRepository.findById(employeeForUpdate.getEmployeeId()).isPresent()
            ? Optional.of(employeeRepository.saveAndFlush(employeeForUpdate))
            : Optional.empty();
  }

  public void deleteEmployee(int employeeId) {
    employeeRepository.deleteById(employeeId);
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }
}
