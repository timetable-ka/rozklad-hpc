package com.example.demo.json.lessons;

import com.example.demo.json.room.RoomConverter;
import com.example.demo.json.room.RoomDto;
import com.example.demo.json.teacher.TeacherConverter;
import com.example.demo.json.teacher.TeacherDto;
import com.example.demo.model.entity.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class LessonsConverter implements Converter<TimeTable, LessonsDto> {

    private final RoomConverter roomConverter;
    private final TeacherConverter teacherConverter;

    @Override
    public LessonsDto convert(TimeTable source) {
        Teacher teacher = source.getTeacher();
        Room room = source.getRoom();
        TeacherDto teacherDto = requireNonNull(teacherConverter.convert(teacher));
        RoomDto roomDto = requireNonNull(roomConverter.convert(room));

        return LessonsDto.builder()
                .lessonId(String.valueOf(source.getId()))
                .groupId(String.valueOf(source.getGroup().getId()))
                .lessonName(source.getLesson().getName())
                .lessonFullName(source.getLesson().getName())
                .lessonNumber(String.valueOf(source.getLessonNumber()))
                .lessonRoom(source.getRoom().getName())
                .teacherName(teacher.getName())
                .teachers(List.of(teacherDto))
                .rooms(List.of(roomDto))
                .dayName(requireNonNull(Day.findByKey(source.getDayNumber())).getName())
                .lessonType("Лек")
                .timeStart("10:25:00")
                .timeEnd("12:00:00")
                .lessonWeek(String.valueOf(source.getLessonWeek()))
                .build();
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

        final String name;
        final long numberDayInWeek;

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
