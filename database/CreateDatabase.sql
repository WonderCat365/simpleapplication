CREATE TABLE employee
(
    employee_id SERIAL NOT NULL,
    first_name varchar(50) NOT NULL ,
    last_name varchar(50) NOT NULL,
    department_id integer NOT NULL,
    job_title varchar(50) NOT NULL,
    gender varchar(50) NOT NULL,
    date_of_birth date NOT NULL,
    PRIMARY KEY (employee_id)
);