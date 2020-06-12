

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

-- pwd for adarsh k123
-- pwd for radha s123

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('adarsh', '21a4ed0a0cf607e77e93bf7604e2bb1ad07757c5', 1),
	('radha', '904752ad9c4ae4186c4b4897321c517de0618702', 1);

INSERT INTO `authorities` (`username`, `authority`) VALUES
	('adarsh', 'ROLE_ADMIN'),
	('radha', 'ROLE_USER');



