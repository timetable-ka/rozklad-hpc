CREATE SCHEMA IF NOT EXISTS timetable3;

DROP TABLE timetable3.college_group;
DROP TABLE timetable3.lesson;
DROP TABLE timetable3.room;
DROP TABLE timetable3.teacher;
DROP TABLE timetable3.timetable;

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
    id                  MEDIUMINT NOT NULL AUTO_INCREMENT,
    teacher_id          int       not null,
    teacher_id_optional int,
    lesson_id           int       not null,
    group_id            int       not null,
    lesson_room_id      int,
    lesson_number       int,
    day_number          int,
    time_start          text,
    time_end            text,
    lesson_week         int,
    PRIMARY KEY (id)
);
