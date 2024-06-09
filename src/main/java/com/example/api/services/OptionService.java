package com.example.api.services;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.option.HandlerOptionResponse;
import com.example.api.domain.dtos.option.OptionRequest;
import com.example.api.domain.dtos.option.OptionResponse;

import java.util.List;
import java.util.UUID;

public interface OptionService {

    Response<HandlerOptionResponse> save(String productId, OptionRequest request);

    Response<List<OptionResponse>> findAll(String productId);

    Response<OptionResponse> findOne(String productId, UUID optionId);

    Response<HandlerOptionResponse> update(String productId, UUID optionId, OptionRequest optionRequest);

    void delete(String productId, UUID optionId);

}


