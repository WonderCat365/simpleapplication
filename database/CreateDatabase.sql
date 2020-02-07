CREATE TYPE gender_type AS ENUM ('MALE','FEMALE');

DROP TYPE gender_type;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee
(
    employee_id   SERIAL      NOT NULL,
    first_name    varchar(50) NOT NULL,
    last_name     varchar(50) NOT NULL,
    department_id integer     NOT NULL,
    job_title     varchar(50) NOT NULL,
    gender        varchar(6)  NOT NULL,
    date_of_birth date        NOT NULL,
    PRIMARY KEY (employee_id)
);

INSERT INTO employee (first_name, last_name, department_id, job_title, gender, date_of_birth)
VALUES ('EMPL_1_FN', 'EMPL_1_LN', 1, 'JOB_TITTLE', 'MALE', '2020-10-10');

SELECT *
FROM employee;
