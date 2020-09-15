package com.example.demo.json.teacher;

import com.example.demo.model.entity.Teacher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TeacherConverter implements Converter<Teacher, TeacherDto> {
    @Override
    public TeacherDto convert(Teacher teacher) {
        return TeacherDto.builder()
                .teacherId(String.valueOf(teacher.getId()))
                .teacherFullName(teacher.getName())
                .teacherName(teacher.getName())
                .teacherShortName(teacher.getName())
                .build();
    }
}
