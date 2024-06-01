package com.example.api.common.mappers;

public interface Mapper<A, B> {
    B mapTo(B a);

    A mapFrom(B b);
}
