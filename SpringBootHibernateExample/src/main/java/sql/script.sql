DROP TABLE IF EXISTS `espark`.`users`;
CREATE TABLE  `espark`.`users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(80) NOT NULL,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into users(id,email,name)
values(1,'adarsh@kumar','adarsh')
,(2,'amit@kumar','amit');
,(3,'radha@singh','radha');