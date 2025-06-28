package com.eazybites.cards.exception.dto;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class ApiError {
    private String apiPath;
    private String status;
    private Integer code;
    private LocalDateTime errorTime;

    @JsonUnwrapped
    private ErrorBody body;

        public static ApiError createWithMessage(String apiPath, HttpStatus status, String message) {
       return ApiError.builder()
               .apiPath(apiPath)
               .status(status.name())
               .code(status.value())
               .errorTime(LocalDateTime.now())
               .body(new MessageBody(message)) 
               .build();    
    }

    public static ApiError createWithDetails(String apiPath, HttpStatus status, Map<String, String> details) {
        return ApiError.builder()
                .apiPath(apiPath)
                .status(status.name())
                .code(status.value())
                .errorTime(LocalDateTime.now())
                .body(new DetailsBody(details)) 
                .build();
    }
}
