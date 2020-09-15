package com.example.demo.json.lessons;


import com.example.demo.json.room.RoomDto;
import com.example.demo.json.teacher.TeacherDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LessonsDto {

    @JsonProperty("lesson_id")
    String lessonId;

    @JsonProperty("group_id")
    String groupId;

    @JsonProperty("day_name")
    String dayName;

    @JsonProperty("lesson_name")
    String lessonName;

    @JsonProperty("lesson_full_name")
    String lessonFullName;

    @JsonProperty("lesson_number")
    String lessonNumber;

    @JsonProperty("lesson_room")
    String lessonRoom;

    @JsonProperty("lesson_type")
    String lessonType;

    @JsonProperty("teacher_name")
    String teacherName;

    @JsonProperty("lesson_week")
    String lessonWeek;

    @JsonProperty("time_start")
    String timeStart;

    @JsonProperty("time_end")
    String timeEnd;

    @JsonProperty("rate")
    String rate;

    @JsonProperty("rooms")
    List<RoomDto> rooms;

    @JsonProperty("teachers")
    List<TeacherDto> teachers;
}
