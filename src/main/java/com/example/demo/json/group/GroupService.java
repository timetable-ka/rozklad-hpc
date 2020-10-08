package com.example.demo.json.group;

import com.example.demo.json.ResponseDto;
import com.example.demo.model.repo.GroupRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.demo.json.FileUtil.createFile;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final ConversionService conversionService;
    private final ObjectMapper objectMapper;


    public void buildGroupSearchJson(){
        List<GroupDto> groupDtos = groupRepository.findAll()
                .stream()
                .map(group -> conversionService.convert(group, GroupDto.class))
                .collect(Collectors.toList());

        String json = null;
        try {
            ResponseDto<Object> responseDto = ResponseDto.builder().data(groupDtos).build();
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDto);
        } catch (Exception e) {
            log.error("Error", e);
        }

        createFile(json.getBytes(), "groups\\search.json");
        log.info("Finish build");
    }

    public void buildGroupInfoById(){
        Function<GroupDto, String> groupDtoFunction = (groupDto) -> String.valueOf(groupDto.getId());
        createJson("id", groupDtoFunction);
    }

    public void buildGroupInfoByName(){
        Function<GroupDto, String> groupDtoFunction = GroupDto::getFullName;
        createJson("name", groupDtoFunction);
    }

    private void createJson(final String prefix,  final Function<GroupDto, String> groupDtoFunction ) {
        groupRepository.findAll()
                .forEach(group -> {
                    GroupDto groupDto = conversionService.convert(group, GroupDto.class);
                    String json;
                    try {
                        ResponseDto<Object> responseDto = ResponseDto.builder().data(groupDto).build();
                        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDto);
                        createFile(json.getBytes(), "groups\\" + prefix + "\\" + groupDtoFunction.apply(groupDto) + ".json");
                    } catch (Exception e) {
                        log.error("Error", e);
                    }
                });
        log.info("Finish build");
    }

}
