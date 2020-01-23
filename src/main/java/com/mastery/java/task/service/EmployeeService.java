package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * Integer argument is a result of JDBC operation. If this argument is 1 so
     * query is successfully passed and method can provide result instance.
     * If Integer is not 1 functional interface returns Optional.empty()
     */
    private BiFunction<Integer, Employee, Optional<Employee>> convertJdbcResultToOptional =
            (result, employee) -> result == 1
                    ? Optional.of(employee)
                    : Optional.empty();

    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    /**
     * Adds new Employee instance into database. Method save() returns number
     * of rows successfully passed. If number is 1 so instance is added into
     * database.
     *
     * @param employee instance of Employee for adding into database
     * @return instance of added object or null
     */
    public Optional<Employee> createEmployee(Employee employee) {

        int result = employeeDao.save(employee);

        return convertJdbcResultToOptional.apply(result, employee);
    }

    public Optional<Employee> updateEmployee(Employee employee,
                                             Long employeeId) {
        int result = employeeDao.update(employee, employeeId);

        return convertJdbcResultToOptional.apply(result, employee);
    }

    public void deleteEmployee(Long employeeId){

        employeeDao.deleteById(employeeId);
    }

    public Optional<Employee> getEmployeeById(Long employeeId){
        return employeeDao.findById(employeeId);
    }

    public int getCountOfEmployee(){
        return employeeDao.count();
    }


}
