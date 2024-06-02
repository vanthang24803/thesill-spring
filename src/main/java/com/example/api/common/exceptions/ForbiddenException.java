package com.example.api.common.exceptions;

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
        this.apiError = new ApiError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
                LocalDateTime.now());
    }

}
