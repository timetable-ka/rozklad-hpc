package com.example.demo.json;

import lombok.*;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {

    private final int statusCode = 200;
    private final Instant timeStamp = Instant.ofEpochMilli(1600162757613L);
    private final String message = "Ok";
    private String debugInfo;
    private String meta;
    private T data;

}
