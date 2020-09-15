package com.example.demo.json.room;

import com.example.demo.json.room.RoomDto;
import com.example.demo.model.entity.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RoomConverter implements Converter<Room, RoomDto> {
    @Override
    public RoomDto convert(Room room) {
        return RoomDto.builder()
                .roomId(String.valueOf(room.getId()))
                .roomName(room.getName())
                .roomLatitude("50.4459415")
                .roomLongitude("30.4547315")
                .build();
    }
}
