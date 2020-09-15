package com.example.demo.json.teacher;

import com.example.demo.json.ResponseDto;
import com.example.demo.json.lessons.LessonsDto;
import com.example.demo.model.entity.Group;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.repo.GroupRepository;
import com.example.demo.model.repo.TeacherRepository;
import com.example.demo.model.repo.TimeTableRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.demo.json.FileUtil.createFile;
import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository timeTableRepository;
    private final GroupRepository groupRepository;
    private final ConversionService conversionService;
    private final ObjectMapper objectMapper;


    @Transactional
    public void buildTeacherInfoByName() {
       groupRepository.findAll().forEach(group -> {
           List<Teacher> teachers = timeTableRepository.findByTimeTableGroupName(group.getName());
           teachers.addAll(timeTableRepository.findByTimeTable2GroupName(group.getName()));

           List<TeacherDto> teacherDtos = teachers
                   .stream()
                   .map(t -> conversionService.convert(t, TeacherDto.class))
                   .filter(Objects::nonNull)
                   .collect( Collectors.toList());

           teacherDtos.forEach(teacherDto -> {
               String json = null;
               try {
                   ResponseDto<Object> responseDto = ResponseDto.builder().data(teacherDtos).build();
                   json = objectMapper.writeValueAsString(responseDto);
               } catch (Exception e) {
                   log.error("Error", e);
               }

               createFile(json.getBytes(), "C:\\Users\\pc\\IdeaProjects\\demo\\groups\\" + group.getId() + "\\teachers.json");
               log.info("Finish build");
           });
        });
    }
}
