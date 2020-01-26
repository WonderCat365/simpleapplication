package com.mastery.java.task.entity;

import com.mastery.java.task.dto.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Employee {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private Long departmentId;
    private String jobTittle;
    private Gender gender;
    private Date dateOfBirth;
}
