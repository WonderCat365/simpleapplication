package com.mastery.java.task.interfases;

import com.mastery.java.task.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    int count();

    int save(Employee employee);

    int update(Employee employee);

    int deleteById(Long employeeId);

    List<Employee> findAll();

    Optional<Employee> findById();
}
