package com.example.demo.json.lessons;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TeacherDto {

    @JsonProperty("teacher_id")
    String teacherId;

    @JsonProperty("teacher_name")
    String teacherName;

    @JsonProperty("teacher_full_name")
    String teacherFullName;

    @JsonProperty("teacher_short_name")
    String teacherShortName;

    @JsonProperty("teacher_url")
    String teacherUrl;

    @JsonProperty("teacher_rating")
    String teacherRating;

}
