package com.example.thesillapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private final ApiError apiError;

    public ForbiddenException() {
        super();
        this.apiError = new ApiError( HttpStatus.FORBIDDEN.value(), "Forbidden", LocalDateTime.now());
    }

}
