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
    private final long timeStamp = 1600162757602L;
    private final String message = "Ok";
    private String debugInfo;
    private String meta;
    private T data;

}
