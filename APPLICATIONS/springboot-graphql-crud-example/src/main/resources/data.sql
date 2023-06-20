DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL,
  gender VARCHAR(8),
  salary INT NOT NULL,
  doj DATE
);

INSERT INTO employee (first_name, last_name, career, salary, gender, doj) VALUES
  ('adarsh', 'kumar', 'It', 10, 'MALE', '2020-01-1'),
  ('radha', 'singh', 'IT', 10, 'FEMALE', '2020-01-1'),
  ('sonu', 'singh', 'IT', 5, 'MALE', '2020-01-1'),
  ('amit', 'kumar', 'Finance',8, 'MALE', '2020-01-1');