
DROP TABLE IF EXISTS permission;

CREATE TABLE permission(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    name VARCHAR(255),
);

DROP TABLE IF EXISTS role;
CREATE TABLE role(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    name VARCHAR(255)
);

DROP TABLE IF EXISTS permission_role;
CREATE TABLE permission_role(
    role_id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    permission_id INTEGER NOT NULL
);

DROP TABLE IF EXISTS role_user;
CREATE TABLE role_user(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL
);

DROP TABLE IF EXISTS user;
CREATE TABLE user(
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    account_expired BOOLEAN,
    account_locked BOOLEAN,
    credenntials_expired BOOLEAN,
    email VARCHAR(255),
    enable BOOLEAN NOT NULL,
    password VARCHAR(255),
    uesrnname VARCHAR(255)
);
