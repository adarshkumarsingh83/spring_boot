

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dob` date DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(65) NOT NULL,
  `last_name` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fopic1oh5oln2khj8eat6ino0` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
INSERT INTO `employee`
 VALUES (1,'2020-02-01','adarsh@espark','Adarsh','Singh')
         ,(2,'2020-02-01','radha@espark','Radha','Singh');
UNLOCK TABLES;

--
-- Dumping routines for database 'testdb'
--

DROP PROCEDURE IF EXISTS `GET_EMPLOYEE_BY_EMAIL` ;
DELIMITER ;;
CREATE  PROCEDURE `GET_EMPLOYEE_BY_EMAIL`(IN employeeEmail VARCHAR(100))
BEGIN
    SELECT * FROM employee WHERE email = employeeEmail ORDER BY id;
END ;;
DELIMITER ;


DROP PROCEDURE IF EXISTS `GET_ALL_EMPLOYEES` ;
DELIMITER ;;
CREATE  PROCEDURE `GET_ALL_EMPLOYEES`()
BEGIN
    SELECT * FROM employee ORDER BY id;
END ;;
DELIMITER ;
