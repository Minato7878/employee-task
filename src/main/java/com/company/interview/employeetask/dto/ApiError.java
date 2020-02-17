package com.company.interview.employeetask.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ApiError {
    String id;
    int status;
    String error;
    String message;
    String path;
    LocalDateTime timestamp;

    private static final String EXCEPTION_ID_PREFIX = "exception-";

    public static  ApiError buildError(Exception e, HttpStatus status, HttpServletRequest request) {
        return ApiError.builder()
                .id(EXCEPTION_ID_PREFIX + UUID.randomUUID().toString())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(e != null ? e.getMessage() : status.getReasonPhrase())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();
    }
}