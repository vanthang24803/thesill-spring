package com.example.api.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Getter
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    private final ApiError apiError;

    public InternalServerException() {
        super();
        this.apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                , LocalDateTime.now());
    }

}
