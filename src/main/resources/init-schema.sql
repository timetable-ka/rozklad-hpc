CREATE SCHEMA IF NOT EXISTS timetable3;

CREATE TABLE IF NOT EXISTS timetable3.college_group
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS timetable3.lesson
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS timetable3.teacher
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS timetable3.room
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS timetable3.timetable
(
    id   MEDIUMINT NOT NULL AUTO_INCREMENT,
    teacher_id int not null, /*1*/
    teacher_id_optional int  , /*1*/
    lesson_id int not null,  /*1*/
    group_id int not null,   /*1*/
    lesson_room_id int,         /*707-22*/
    lesson_number int,         /*707-22*/
    day_number int,           /*Понеділок =1*/
    time_start text,         /*12:00:00*/
    time_end text,           /*14:00:00*/
    lesson_week int,         /*1*/
    PRIMARY KEY (id)
);


TRUNCATE TABLE timetable3.college_group;
TRUNCATE TABLE timetable3.lesson;
TRUNCATE TABLE timetable3.room;
TRUNCATE TABLE timetable3.teacher;
TRUNCATE TABLE timetable3.timetable;
