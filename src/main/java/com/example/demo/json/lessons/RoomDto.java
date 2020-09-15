package com.example.demo.json.lessons;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomDto {

    @JsonProperty("room_id")
    String roomId;

    @JsonProperty("room_name")
    String roomName;

    @JsonProperty("room_latitude")
    String roomLatitude;

    @JsonProperty("room_longitude")
    String roomLongitude;

}
