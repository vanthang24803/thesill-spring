package com.example.api.services;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.color.ColorRequest;
import com.example.api.domain.dtos.color.ColorResponse;
import com.example.api.domain.dtos.color.CreateColorRequest;
import com.example.api.domain.dtos.color.RemoveColorRequest;

import java.util.List;
import java.util.UUID;

public interface ColorService {

    Response<List<ColorResponse>> save(UUID optionId, CreateColorRequest requests);

    Response<List<ColorResponse>> findAll(UUID optionId);

    Response<ColorResponse> findOne(UUID optionId, UUID colorId);

    Response<ColorResponse> update(UUID optionId, UUID colorId, ColorRequest request);

    void remove(UUID optionId, UUID colorId);

    void removeMany(UUID optionId, RemoveColorRequest requests);

    void clear(UUID optionId);
}
