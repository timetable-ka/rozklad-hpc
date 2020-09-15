package com.example.demo.json.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GroupDto {

    @JsonProperty("group_id")
    private Long id;

    @JsonProperty("group_full_name")
    private String fullName;

    @JsonProperty("group_prefix")
    private String prefix;

    @JsonProperty("group_okr")
    private String okr;

    @JsonProperty("group_type")
    private String type;

    @JsonProperty("group_url")
    private String url;
}
