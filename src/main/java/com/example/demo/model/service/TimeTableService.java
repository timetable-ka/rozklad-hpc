package com.example.demo.model.service;

import com.example.demo.model.entity.*;
import com.example.demo.model.repo.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;


    public void createNewLessons(String lessonsName,
                          long lessonsNumber,
                          int lessonsWeek,
                          String firstTeacherName,
                          String secondTeacherName,
                          String roomNumber,
                          String groupName) {
        Lesson lesson = new Lesson();
        lesson.setName(lessonsName);

        Teacher teacher = new Teacher();
        teacher.setName(firstTeacherName);

        Teacher teacherSecond = new Teacher();
        teacherSecond.setName(secondTeacherName);

        Room room = new Room();
        room.setName(roomNumber);

        Group group = new Group();
        group.setName(groupName);


        // todo main object
        TimeTable timeTable = new TimeTable();
        timeTable.setLesson(lesson);
        timeTable.setLessonNumber(getNumber(lessonsNumber));
        timeTable.setLessonWeek(lessonsWeek);

        timeTable.setTeacher(teacher);
        timeTable.setTeacherSecond(teacherSecond);

        timeTable.setRoom(room);

        timeTable.setGroup(group);
        System.err.println(timeTable.toString());
        timeTableRepository.save(timeTable);
    }

    private long getNumber(long lessonsNumber) {
        if (lessonsNumber == 4) {
            return 4;
        }
        return lessonsNumber % 4 == 0 ? 4 : lessonsNumber % 4;
    }
}
