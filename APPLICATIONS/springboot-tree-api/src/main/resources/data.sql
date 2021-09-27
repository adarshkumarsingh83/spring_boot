DROP TABLE IF EXISTS department;
CREATE TABLE department (
  dept_id INT AUTO_INCREMENT  PRIMARY KEY,
  dept_name VARCHAR(250) NOT NULL,
  parent_dept_Id INT DEFAULT NULL
);


INSERT INTO department (dept_id,dept_name, parent_dept_Id) VALUES
  (101,'management',0),
  (1111,'It',101),
  (2222,'Finance',101),
  (3333,'HR',101),
  (4444,'OP',101);

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  designation VARCHAR(250) NOT NULL,
  manager_Id INT DEFAULT NULL,
  dept_Id INT DEFAULT NULL
);

INSERT INTO employee (id,name, designation, manager_Id,dept_Id) VALUES
  (1,'radha', 'founder-mgr', 0,101),
  (100,'adarsh', 'it-mgr', 1,1111),
  (130, 'vishal', 'developer', 100, 1111),
  (120, 'mulla', 'it-admin', 100, 1111),
  (131, 'bbb', 'sr-developer', 100, 1111),
  (133, 'ccc', 'developer', 100, 1111),
  (135, 'ddd', 'jr-developer', 100, 1111),
  (138, 'eee', 'developer', 100, 1111),
  (110, 'ajit', 'it-lead', 100, 1111),
  (121, 'aaa', 'developer', 110, 1111),
  (200,'amit', 'finance-mgr', 1,2222),
  (220, '111', 'ca', 200, 3333),
  (230, '222', 'ca', 200, 3333),
  (500,'hr-1', 'hr-mgr', 1,3333),
  (5050, 'hr-2', 'hr', 500, 2222),
  (6000, 'hr-2', 'hr', 500, 2222),
  (400,'op-1', 'op-mgr', 1,4444),
  (1001, 'op-2', 'op', 400, 4444),
  (1002, 'op-3', 'op', 400, 4444);

