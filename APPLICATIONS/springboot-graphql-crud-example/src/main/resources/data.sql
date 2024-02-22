DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL,
  gender VARCHAR(8),
  salary INT NOT NULL,
  doj DATE,
  attributes VARCHAR(250) DEFAULT NULL,
  phone VARCHAR(250) DEFAULT NULL
);

INSERT INTO employee (first_name, last_name, career, salary, gender, doj, attributes, phone) VALUES
  ('adarsh', 'kumar', 'It', 10, 'MALE', '2020-01-1', '{"key1":"value"}', '["1234567890","1234567890"]'),
  ('radha', 'singh', 'IT', 10, 'FEMALE', '2020-01-1', '{"key2":"value"}','["1234567890","1234567890"]'),
  ('sonu', 'singh', 'IT', 5, 'MALE', '2020-01-1', '{"key3":"value"}','["1234567890","1234567890"]'),
  ('amit', 'kumar', 'Finance',8, 'MALE', '2020-01-1', '{"key4":"value"}','["1234567890","1234567890"]');