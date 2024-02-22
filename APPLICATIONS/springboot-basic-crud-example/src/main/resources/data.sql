DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

INSERT INTO employee (first_name, last_name, career) VALUES
  ('adarsh', 'kumar', 'It'),
  ('radha', 'singh', 'IT'),
  ('sonu', 'singh', 'IT'),
  ('amit', 'kumar', 'Finance');