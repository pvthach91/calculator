CREATE DATABASE calculation;

USE calculation;

CREATE TABLE user (
  username VARCHAR(30) NOT NULL,
  password text NOT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE history (
  id int(20) NOT NULL AUTO_INCREMENT,
  content text NOT NULL,
  date date NOT NULL,
  created_by VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (created_by) REFERENCES user(username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;