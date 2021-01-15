DROP TABLE IF EXISTS `employee`;
CREATE TABLE  `employee` (
  `employeeId` int(11) NOT NULL,
  `employeeFirstName` varchar(50) NOT NULL,
  `employeeLastName` varchar(50) NOT NULL,
  `employeeEmail` varchar(50) NOT NULL,
  `employeeSalary` int(15) DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;