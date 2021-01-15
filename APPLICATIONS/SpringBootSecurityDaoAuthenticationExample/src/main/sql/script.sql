drop schema  if  exists `espark`;
CREATE SCHEMA `espark` ;
drop user 'adarsh'@'localhost';
FLUSH PRIVILEGES;
CREATE USER 'adarsh'@'localhost' IDENTIFIED BY 'adarsh';
GRANT ALL PRIVILEGES ON *.* TO 'adarsh'@'localhost' WITH GRANT OPTION;

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- pwd for adarsh adarsh
-- pwd for radha radha

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('adarsh', '107c87ac2e88420d86be246580b38e70e5d99108', 1),
	('radha', 'b2b47e7335b864ae25aacda590303c45f4c704f3', 1);

INSERT INTO `authorities` (`username`, `authority`) VALUES
	('adarsh', 'ROLE_ADMIN'),
	('radha', 'ROLE_USER');



