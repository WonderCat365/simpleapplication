package com.mastery.java.task.dao;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.interfases.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@PropertySource("classpath:sqlRequest.properties")
public class EmployeeDao implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    Environment environment;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject(environment
                .getProperty("employee.count.request"), Integer.class);
    }

    @Override
    public int save(Employee employee) {

        return jdbcTemplate.update(environment
                        .getProperty("employee.insert.request"),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTittle(),
                employee.getGender(),
                employee.getDateOfBirth());
    }

    @Override
    public int update(Employee employee, Long employeeId) {
        return jdbcTemplate.update(environment
                        .getProperty("employee.updateById.request"),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getJobTittle(),
                employee.getGender(),
                employee.getDateOfBirth(),
                employeeId);
    }

    @Override
    public void deleteById(Long employeeId) {
        jdbcTemplate.update(environment
                .getProperty("employee.deleteById.request"), employeeId);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(environment
                        .getProperty("employee.listAll.request"),
                (rs, newRow) -> new Employee(
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getLong("department_id"),
                        rs.getString("job_title"),
                        rs.getString("gender"),
                        rs.getDate("date_of_birth")));
    }

    @Override
    public Optional<Employee> findById(Long employeeId) {
        Optional<Employee> employee = jdbcTemplate.queryForObject(environment.
                        getProperty("employee.findById.request"),
                new Object[]{employeeId},
                (rs, rowNum) -> Optional.of(new Employee(
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getLong("department_id"),
                        rs.getString("job_title"),
                        rs.getString("gender"),
                        rs.getDate("date_of_birth")
                ))
        );
        return employee;
    }
}
