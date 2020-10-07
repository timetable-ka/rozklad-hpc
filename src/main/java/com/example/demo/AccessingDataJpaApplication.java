package com.example.demo;

import com.example.demo.converter.ExportService;
import com.example.demo.json.group.GroupService;
import com.example.demo.json.lessons.LessonService;
import com.example.demo.json.teacher.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class AccessingDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    public static void exitApplication(ConfigurableApplicationContext ctx) {
        int exitCode = SpringApplication.exit(ctx, () -> 0);
        System.exit(exitCode);
    }

    @Bean
    public CommandLineRunner demo(GroupService groupService,
                                  LessonService lessonService,
                                  TeacherService teacherService,
                                  ExportService exportService,
                                  ConfigurableApplicationContext ctx) {
        return (args) -> {
            buildFile(groupService, lessonService, teacherService, ctx);
//            exportService.convertExcel();
            exitApplication(ctx);
        };
    }

    private void buildFile(GroupService groupService, LessonService lessonService, TeacherService teacherService, ConfigurableApplicationContext ctx) {
        teacherService.buildTeacherInfoByName();
        lessonService.buildTeacherInfoForEveryTeacher();
        lessonService.buildLessonInfoByName();
        groupService.buildGroupInfoById();
        groupService.buildGroupInfoByName();
        groupService.buildGroupSearchJson();
    }

}
