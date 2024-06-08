package com.example.api.repositories;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.tag.TagRequest;
import com.example.api.domain.entities.TagEntity;

import java.util.List;
import java.util.UUID;

public interface TagService {
    void seeds();

    Response<TagEntity> save(TagRequest request);

    Response<List<TagEntity>> findAll();

    Response<TagEntity> findOne(UUID id);

    Response<TagEntity> update(UUID id, TagRequest request);

    void remove(UUID id);
}
