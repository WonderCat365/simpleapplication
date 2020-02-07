package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee createEmployee(Employee employee) {

        return employeeDao.createEmployee(employee);
    }

    public Optional<Employee> updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(int employeeId) {

        employeeDao.deleteEmployee(employeeId);
    }

    public Optional<Employee> getEmployeeById(int employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }
}
