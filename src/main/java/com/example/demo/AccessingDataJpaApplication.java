package com.example.demo;

import com.example.demo.json.group.GroupService;
import com.example.demo.model.repo.TimeTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

@Slf4j
@SpringBootApplication
public class AccessingDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(GroupService groupService, TimeTableRepository timeTableRepository) {
        return (args) -> {

            timeTableRepository.findAll()
                    .forEach(timeTable -> log.error(timeTable.toString()));
//            groupService.buildGroupInfoById();
//            groupService.buildGroupInfoByName();
//            groupService.buildGroupSearchJson();
        };
    }

}
