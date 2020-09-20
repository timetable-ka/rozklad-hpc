package com.example.demo.json.lessons;

import com.example.demo.json.ResponseDto;
import com.example.demo.model.entity.TimeTable;
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
import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService {

    private final TimeTableRepository timeTableRepository;
    private final TeacherRepository teacherRepository;
    private final ConversionService conversionService;
    private final ObjectMapper objectMapper;


    public void buildLessonInfoByName() {
        Map<String, List<LessonsDto>> lessonsDtos = timeTableRepository.findAll()
                .stream()
                .map(group -> conversionService.convert(group, LessonsDto.class))
                .filter(Objects::nonNull)
                .filter(timeTable -> nonNull(timeTable.getGroupId()))
                .collect(Collectors.groupingBy(LessonsDto::getGroupId, Collectors.mapping(Function.identity(), Collectors.toList())));

        lessonsDtos.forEach((groupId, lessonsDto) -> {
            String json = null;
            try {
                ResponseDto<Object> responseDto = ResponseDto.builder().data(lessonsDto).build();
                json = objectMapper.writeValueAsString(responseDto);
            } catch (Exception e) {
                log.error("Error", e);
            }

            createFile(json.getBytes(), "groups\\" + groupId + "\\lessons.json");
            log.info("Finish build");
        });
    }



    @Transactional
    public void buildTeacherInfoForEveryTeacher() {
        teacherRepository.findAll().forEach(teacher -> {
            List<TimeTable> timeTables = timeTableRepository.findByTeacherId(teacher.getId());
            timeTables.addAll(timeTableRepository.findByTeacherSecondId(teacher.getId()));

            List<LessonsDto> lessonsDtos = timeTables.stream()
                    .map(t -> conversionService.convert(t, LessonsDto.class))
                    .filter(Objects::nonNull)
                    .collect( Collectors.toList());

            lessonsDtos.forEach(teacherDto -> {
                String json = null;
                try {
                    ResponseDto<Object> responseDto = ResponseDto.builder().data(lessonsDtos).build();
                    json = objectMapper.writeValueAsString(responseDto);
                } catch (Exception e) {
                    log.error("Error", e);
                }

                createFile(json.getBytes(), "teachers\\" + teacher.getId() + "\\lessons.json");
                log.info("Finish build");
            });
        });
    }
}
