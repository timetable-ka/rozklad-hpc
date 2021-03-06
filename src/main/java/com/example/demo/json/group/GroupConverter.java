package com.example.demo.json.group;

import com.example.demo.model.entity.Group;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class GroupConverter implements Converter<Group, GroupDto> {

    @Override
    public GroupDto convert(Group source) {
        return GroupDto.builder()
                .id(source.getId())
                .fullName(source.getName())
                .okr("bachelor")
                .prefix(source.getName().substring(0, 2).toLowerCase())
                .type("extramural")
                .url("http://google.com")
                .build();
    }

}
