DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  id bigint NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL,
   PRIMARY KEY (id)
);

INSERT INTO employee (first_name, last_name, career) VALUES
  ('adarsh', 'kumar', 'It'),
  ('radha', 'singh', 'IT'),
  ('sonu', 'singh', 'IT'),
  ('amit', 'kumar', 'Finance');