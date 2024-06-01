package com.example.api.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private final ApiError apiError;

    public BadRequestException(String message) {
        super(message);
        this.apiError = new ApiError( HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now());
    }

}
