use espark;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE  `employee` (
  `employeeId` int(11) NOT NULL,
  `employeeFirstName` varchar(50) NOT NULL,
  `employeeLastName` varchar(50) NOT NULL,
  `employeeEmail` varchar(50) NOT NULL,
  `employeeSalary` int(15) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Employee(employeeId,employeeFirstName,employeeLastName,employeeEmail,employeeSalary) VALUES (100,'Adarsh','Kumar','adarsh@kumar', 500);
INSERT INTO Employee(employeeId,employeeFirstName,employeeLastName,employeeEmail,employeeSalary) VALUES (101,'Amit','Kumar','amit@kumar', 500);
INSERT INTO Employee(employeeId,employeeFirstName,employeeLastName,employeeEmail,employeeSalary) VALUES (103,'Radha','Singh','radha@singh', 500);
INSERT INTO Employee(employeeId,employeeFirstName,employeeLastName,employeeEmail,employeeSalary) VALUES (104,'Sonu','Singh','sonu@singh', 500);
INSERT INTO Employee(employeeId,employeeFirstName,employeeLastName,employeeEmail,employeeSalary) VALUES (105,'Monu','Singh','monu@singh', 500);