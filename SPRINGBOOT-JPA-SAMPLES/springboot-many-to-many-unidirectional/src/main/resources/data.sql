
drop table IF EXISTS contact;

create table contact (
contact_id bigint generated by default as identity
, country varchar(255)
, state varchar(255)
, street varchar(255)
, primary key (contact_id));

drop table IF EXISTS employee;

create table employee (
employee_id bigint generated by default as identity
, career varchar(255)
, first_name varchar(255)
, last_name varchar(255)
, primary key (employee_id));

drop table IF EXISTS employee_contact;

create table employee_contact (
employee_id bigint not null
, contact_id bigint not null);

alter table employee_contact add constraint FK_contact_id_contact foreign key (contact_id) references contact;
alter table employee_contact add constraint FK_employee_id_employee foreign key (employee_id) references employee;


insert into contact (contact_id,street, state, country) values
  (10,'bang street', 'mp', 'in'),
  (20,'hyd street', 'ap', 'in'),
  (30,'ald street', 'up', 'in'),
  (40,'delhi street', 'delhi', 'in');

insert into employee (employee_id,first_name, last_name, career) values
  (10,'adarsh', 'kumar', 'It'),
  (20,'radha', 'singh', 'IT'),
  (30,'sonu', 'singh', 'IT'),
  (40,'amit', 'kumar', 'Finance');

insert into employee_contact(employee_id,contact_id) values
(10,10),
(20,20),
(30,30),
(40,40);