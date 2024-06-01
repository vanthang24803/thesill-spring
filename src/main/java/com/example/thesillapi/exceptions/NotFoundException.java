package com.example.thesillapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class NotFoundException extends RuntimeException {
    private final ApiError apiError;

    public NotFoundException(String message) {
        super(message);
        this.apiError = new ApiError(HttpStatus.NOT_FOUND.value(),
                message, LocalDateTime.now());
    }

}
