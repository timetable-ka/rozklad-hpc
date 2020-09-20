package com.example.demo.json.lessons;

import com.example.demo.json.room.RoomConverter;
import com.example.demo.json.room.RoomDto;
import com.example.demo.json.teacher.TeacherConverter;
import com.example.demo.json.teacher.TeacherDto;
import com.example.demo.model.Day;
import com.example.demo.model.LessonTime;
import com.example.demo.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class LessonsConverter implements Converter<TimeTable, LessonsDto> {

    private static final String BLANK_CHAR = "";
    private static final String COMA_WITH_SPACE = ", ";

    private final RoomConverter roomConverter;
    private final TeacherConverter teacherConverter;


    @Override
    public LessonsDto convert(TimeTable source) {
        Teacher teacher = source.getTeacher();
        TeacherDto teacherDto = requireNonNull(teacherConverter.convert(teacher));
        List<TeacherDto> teacherDtos = new LinkedList<>(List.of(teacherDto));

        Room room = source.getRoom();
        RoomDto roomDto = requireNonNull(roomConverter.convert(room));
        List<RoomDto> roomDtos = new LinkedList<>(List.of(roomDto));

        LessonTime lessonTime = requireNonNull(LessonTime.findByKey(source.getLessonNumber()));

        return LessonsDto.builder()
                .lessonId(String.valueOf(source.getId()))
                .groupId(String.valueOf(source.getGroup().getId()))
                .lessonName(source.getLesson().getName())
                .lessonFullName(source.getLesson().getName())
                .lessonNumber(String.valueOf(source.getLessonNumber()))
                .lessonRoom(room.getName() + getSecondRoomNameAndAddToRoom(source, roomDtos))
                .teacherName(teacher.getName() + getSecondTeacherNameAndAddToTeachers(source, teacherDtos))
                .teachers(teacherDtos)
                .rooms(roomDtos)
                .dayName(requireNonNull(Day.findByKey(source.getDayNumber())).getName())
                .dayNumber(String.valueOf(source.getDayNumber()))
                .lessonType("Лек")
                .timeStart(lessonTime.getStart())
                .timeEnd(lessonTime.getEnd())
                .lessonWeek(String.valueOf(source.getLessonWeek()))
                .build();
    }

    private String getSecondTeacherNameAndAddToTeachers(TimeTable source, List<TeacherDto> teacherDtos) {
        Teacher teacherSecond = source.getTeacherSecond();
        TeacherDto teacherSecondDto = Optional.ofNullable(teacherSecond)
                .map(teacherConverter::convert)
                .orElse(null);
        Optional.ofNullable(teacherSecondDto)
                .ifPresent(teacherDtos::add);
        return Optional.ofNullable(teacherSecondDto)
                .map(TeacherDto::getTeacherName)
                .map(s -> COMA_WITH_SPACE + s)
                .orElse(BLANK_CHAR);
    }

    private String getSecondRoomNameAndAddToRoom(TimeTable source, List<RoomDto> roomDtos) {
        Room roomSecond = source.getRoomSecond();
        RoomDto roomSecondDto = Optional.ofNullable(roomSecond)
                .map(roomConverter::convert)
                .orElse(null);
        Optional.ofNullable(roomSecondDto)
                .ifPresent(roomDtos::add);
        return Optional.ofNullable(roomSecondDto)
                .map(RoomDto::getRoomName)
                .map(s -> COMA_WITH_SPACE + s)
                .orElse(BLANK_CHAR);
    }

}
