-- To Check Employee Table Exist then drop
DROP TABLE IF EXISTS employee;

-- To Create Employee Table
CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

-- To Override hibernate sequence and start with 5
DROP SEQUENCE IF EXISTS HIBERNATE_SEQUENCE;
CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE START WITH 5 INCREMENT BY 1 ;

-- To Insert the data into the Employee Table
INSERT INTO employee (first_name, last_name, career) VALUES
  ( 'adarsh', 'kumar', 'It'),
  ( 'radha', 'singh', 'IT'),
  ( 'sonu', 'singh', 'IT'),
  ( 'amit', 'kumar', 'Finance');