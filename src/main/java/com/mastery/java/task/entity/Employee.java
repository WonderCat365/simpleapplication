package com.mastery.java.task.entity;

import com.mastery.java.task.dto.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id")
  private Integer employeeId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "department_id")
  private Long departmentId;

  @Column(name = "job_title")
  private String jobTittle;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", length = 6)
  private Gender gender;

  @Column(name = "date_of_birth")
  private Date dateOfBirth;
}
