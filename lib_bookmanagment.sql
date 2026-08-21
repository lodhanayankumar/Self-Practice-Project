CREATE DATABASE libmanager;

USE libmanager;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100),
    password VARCHAR(100),
    role VARCHAR(50)
);
select* from users;
CREATE TABLE books (
    isbn_no BIGINT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL
);
USE libmanager;
