package com.example.thesillapi.mappers;

public interface Mapper<A, B> {
    B mapTo(B a);

    A mapFrom(B b);
}
