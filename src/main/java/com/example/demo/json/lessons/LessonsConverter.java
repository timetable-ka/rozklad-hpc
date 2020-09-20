package com.example.demo.json.lessons;

import com.example.demo.json.room.RoomConverter;
import com.example.demo.json.room.RoomDto;
import com.example.demo.json.teacher.TeacherConverter;
import com.example.demo.json.teacher.TeacherDto;
import com.example.demo.model.entity.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class LessonsConverter implements Converter<TimeTable, LessonsDto> {

    private final RoomConverter roomConverter;
    private final TeacherConverter teacherConverter;

    @Override
    public LessonsDto convert(TimeTable source) {
        Teacher teacher = source.getTeacher();
        Teacher teacherSecond = source.getTeacherSecond();
        TeacherDto teacherDto = requireNonNull(teacherConverter.convert(teacher));
        List<TeacherDto> teacherDtos = new ArrayList<>(List.of(teacherDto));
        TeacherDto teacherSecondDto = Optional.ofNullable(teacherSecond)
                .map(teacherConverter::convert)
                .orElse(null);
        Optional.ofNullable(teacherSecondDto)
                .ifPresent(teacherDtos::add);
        String secondTeacherName = Optional.ofNullable(teacherSecondDto)
                .map(TeacherDto::getTeacherName)
                .map(s -> ", " + s)
                .orElse("");


        Room room = source.getRoom();
        Room roomSecond = source.getRoomSecond();
        RoomDto roomDto = requireNonNull(roomConverter.convert(room));
        List<RoomDto> roomDtos = new ArrayList<>(List.of(roomDto));

        RoomDto roomSecondDto = Optional.ofNullable(roomSecond)
                .map(roomConverter::convert)
                .orElse(null);
        Optional.ofNullable(roomSecondDto)
                .ifPresent(roomDtos::add);
        String roomSecondName = Optional.ofNullable(roomSecondDto)
                .map(RoomDto::getRoomName)
                .map(s -> ", " + s)
                .orElse("");


        LessonTime lessonTime = LessonTime.findByKey(source.getLessonNumber());


        return LessonsDto.builder()
                .lessonId(String.valueOf(source.getId()))
                .groupId(String.valueOf(source.getGroup().getId()))
                .lessonName(source.getLesson().getName())
                .lessonFullName(source.getLesson().getName())
                .lessonNumber(String.valueOf(source.getLessonNumber()))
                .lessonRoom(room.getName() + roomSecondName)
                .teacherName(teacher.getName() + secondTeacherName)
                .teachers(teacherDtos)
                .rooms(roomDtos)
                .dayName(requireNonNull(Day.findByKey(source.getDayNumber())).getName())
                .dayNumber(String.valueOf(source.getDayNumber()))
                .lessonType("Лек")
                .timeStart(requireNonNull(lessonTime).getStart())
                .timeEnd(lessonTime.getEnd())
                .lessonWeek(String.valueOf(source.getLessonWeek()))
                .build();
    }

    @RequiredArgsConstructor
    @Getter
    enum LessonTime {

        FIRST("08:30","09:50", 1),
        SECOND("10:00","11:20", 2),
        THIRD("11:50","13:10", 3),
        FOURTH("13:20","14:40", 4),
        FIFTH("14:50","16:10", 4);

        private final String start;
        private final String end;
        private  final long numberDayInWeek;

        public static LessonTime findByKey(long i) {
            LessonTime[] testEnums = LessonTime.values();
            for (LessonTime testEnum : testEnums) {
                if (Long.valueOf(testEnum.getNumberDayInWeek()).equals(i)) {
                    return testEnum;
                }
            }
            return null;
        }
    }

    @RequiredArgsConstructor
    @Getter
    enum Day {

        MON("Понеділок", 1),
        TU("Вівторок", 2),
        WE("Середа", 3),
        TH("Четверг", 4),
        FR("Пятниця", 5),
        SA("Субота", 6),
        SU("Неділя", 7);

        private final String name;
        private final long numberDayInWeek;

        public static Day findByKey(long i) {
            Day[] testEnums = Day.values();
            for (Day testEnum : testEnums) {
                if (Long.valueOf(testEnum.getNumberDayInWeek()).equals(i)) {
                    return testEnum;
                }
            }
            return null;
        }
    }
}
