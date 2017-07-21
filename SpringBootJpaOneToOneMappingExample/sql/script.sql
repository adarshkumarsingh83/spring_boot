DROP TABLE IF EXISTS `espark`.`address`;
CREATE TABLE  `espark`.`address` (
  `address_id` int(11) NOT NULL,
  `address_city` varchar(255) DEFAULT NULL,
  `address_country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO ADDRESS(address_id, address_city,address_country )
VALUES (1,'BANGALORE','INDIA')
,(2,'HYDERABAD','INDIA');

DROP TABLE IF EXISTS `espark`.`employee`;
CREATE TABLE  `espark`.`employee` (
  `employee_id` int(11) NOT NULL,
  `employee_name` varchar(255) NOT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FK_qrbsk9ljmhfje93me0n7xwdxq` (`address_id`),
  CONSTRAINT `FK_qrbsk9ljmhfje93me0n7xwdxq` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO EMPLOYEE(employee_id,employee_name,address_id)
VALUES(1,'ADARSH KUMAR',1)
      ,(2,'RADHA SINGH',2);
