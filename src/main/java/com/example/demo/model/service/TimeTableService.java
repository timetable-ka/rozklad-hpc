package com.example.demo.model.service;

import com.example.demo.model.entity.*;
import com.example.demo.model.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final RoomRepository roomRepository;

    public static void main(String[] args) {
        System.err.println(Arrays.toString("Захватова Л.В.".split("\\. ")));
    }

    public void createNewLessons(String lessonsName,
                                 long lessonsNumber,
                                 int lessonsWeek,
                                 String firstTeacherName,
                                 String secondTeacherName,
                                 String roomNumber,
                                 String groupName) {
        try {
            TimeTable timeTable = new TimeTable();

            Lesson lesson = new Lesson();
            lesson.setName(lessonsName);
            lesson = lessonRepository.findByName(lessonsName).orElse(lesson);
            lessonRepository.saveAndFlush(lesson);


            String[] split = firstTeacherName.split("\\. ");
            if (split.length == 1 || firstTeacherName.equals("Ковалець. І.М.")) {
                saveTeacher(firstTeacherName, secondTeacherName, timeTable);
            } else if (split.length == 2) {
                String firstTeacherNameNew = split[0];
                String[] split1 = firstTeacherNameNew.split(" ");
                if (split1.length == 2) {
                    saveTeacher(firstTeacherNameNew, secondTeacherName, timeTable);
                } else if (split1.length == 3) {
                    saveTeacher(split1[1] + split1[2], secondTeacherName, timeTable);
                }
            }



            Room room = new Room();
            room.setName(roomNumber);
            room = roomRepository.findByName(roomNumber).orElse(room);
            roomRepository.saveAndFlush(room);

            Group group = new Group();
            group.setName(groupName);
            group = groupRepository.findByName(groupName).orElse(group);
            groupRepository.saveAndFlush(group);

            // todo main object

            timeTable.setLesson(lesson);
            timeTable.setLessonNumber(getLessonNumber(lessonsNumber));
            timeTable.setLessonWeek(lessonsWeek);

            timeTable.setRoom(room);

            timeTable.setGroup(group);

            timeTable.setDayNumber(getDayNumber(lessonsNumber));

            timeTableRepository.saveAndFlush(timeTable);
        } catch (Exception e) {
            log.error("Teacher name = {}", firstTeacherName);
            throw e;
        }
    }

    private void saveTeacher(String firstTeacherName, String secondTeacherName, TimeTable timeTable) {
        Teacher teacher = new Teacher();
        teacher.setName(firstTeacherName);
        teacher = teacherRepository.findByName(firstTeacherName).orElse(teacher);
        teacherRepository.saveAndFlush(teacher);

        Teacher teacherSecond = new Teacher();
        teacherSecond.setName(secondTeacherName);
        teacherSecond = teacherRepository.findByName(secondTeacherName).orElse(teacher);
        teacherRepository.saveAndFlush(teacherSecond);

        timeTable.setTeacher(teacher);
        timeTable.setTeacherSecond(teacherSecond);
    }

    private long getDayNumber(long lessonsNumber) {
        if (lessonsNumber > 0 && lessonsNumber <= 4) {
            return 1;
        } else if (lessonsNumber > 4 && lessonsNumber <= 8) {
            return 2;
        } else if (lessonsNumber > 8 && lessonsNumber <= 12) {
            return 3;
        } else if (lessonsNumber > 12 && lessonsNumber <= 16) {
            return 4;
        } else if (lessonsNumber > 16 && lessonsNumber <= 20) {
            return 5;
        } else {
            throw new RuntimeException("Error Lesson number = " + lessonsNumber);
        }
    }

    private long getLessonNumber(long lessonsNumber) {
        if (lessonsNumber == 4) {
            return 4;
        }
        return lessonsNumber % 4 == 0 ? 4 : lessonsNumber % 4;
    }
}
