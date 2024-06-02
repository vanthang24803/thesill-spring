package com.example.api.common.exceptions;

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
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), LocalDateTime.now());

    }

}
