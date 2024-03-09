CREATE DATABASE IF NOT EXISTS showmethemoney;
USE showmethemoney;

CREATE TABLE IF NOT EXISTS Calendar
(
    calid    int auto_increment
        primary key,
    username varchar(45)  not null,
    year     int          not null,
    month    int          not null,
    day      int          not null,
    division varchar(45)  not null,
    money    int          not null,
    category varchar(45)  not null,
    memo     varchar(500) null
);

CREATE TABLE UserEntity
(
    id       int auto_increment
        primary key,
    username varchar(45)  not null,
    password varchar(455) not null,
    role     varchar(45)  not null
);
