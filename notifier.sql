create database notifier;
show databases;
use notifier;
show tables;

describe users;
describe notebook;
describe note;

drop table users;
drop table notebook;
drop table note;

select * from users;
select * from notebook;
select * from note;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) DEFAULT NULL,
  `mobileNumber` BigInt DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `notebook`(
	`nbid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `userid` int NOT NULL,
    `nbname` varchar(250) NOT NULL UNIQUE,
    FOREIGN KEY (`userid`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `note`(
	`nid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nbid` int NOT NULL,
    `userid` int NOT  NULL,
    `nname` varchar(250) NOT NULL UNIQUE,
    `startDate` DATE NOT NULL,
    `endDate` DATE NOT NULL,
    `reminderDate` DATE NOT NULL,
    `status` varchar(250) NOT NULL,
    `tag` varchar(250) NOT NULL,
    `description` LONGTEXT NOT NULL,
    FOREIGN KEY (`nbid`) REFERENCES `notebook`(`nbid`),
    FOREIGN KEY (`userid`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;