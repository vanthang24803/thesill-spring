package com.example.api.domain.dtos.message;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
    public int status;
    public T result;
}