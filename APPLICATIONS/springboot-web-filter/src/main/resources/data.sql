DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL,
  dob  VARCHAR(30)
);

INSERT INTO employee (first_name, last_name, career, dob) VALUES
  ('adarsh', 'kumar', 'It' , '2022-10-08'),
  ('radha', 'singh', 'IT','2022-10-08'),
  ('sonu', 'singh', 'IT','2022-10-08'),
  ('amit', 'kumar', 'Finance','2022-10-08');