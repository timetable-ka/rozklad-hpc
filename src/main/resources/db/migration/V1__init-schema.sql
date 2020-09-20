DROP TABLE IF EXISTS college_group;
DROP TABLE IF EXISTS lesson;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS timetable;

CREATE TABLE IF NOT EXISTS college_group
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS lesson
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS teacher
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS room
(
    id   MEDIUMINT,
    name TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS timetable
(
    id                      MEDIUMINT NOT NULL AUTO_INCREMENT,
    teacher_id              int       not null,
    teacher_id_optional     int,
    lesson_room_id          int       not null,
    lesson_room_id_optional int,
    lesson_id               int       not null,
    group_id                int       not null,
    lesson_number           int,
    day_number              int,
    time_start              text,
    time_end                text,
    lesson_week             int,
    PRIMARY KEY (id)
);
