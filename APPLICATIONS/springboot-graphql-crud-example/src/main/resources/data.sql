DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL,
  salary INT NOT NULL
);

INSERT INTO employee (first_name, last_name, career, salary) VALUES
  ('adarsh', 'kumar', 'It', 10),
  ('radha', 'singh', 'IT', 10),
  ('sonu', 'singh', 'IT', 5),
  ('amit', 'kumar', 'Finance',8);