package com.example.api.common.helpers;

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