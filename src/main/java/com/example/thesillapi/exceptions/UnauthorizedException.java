package com.example.thesillapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
public class UnauthorizedException extends RuntimeException {
    private final ApiError apiError;

    public UnauthorizedException() {
        super();
        this.apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized", LocalDateTime.now());

    }

}
