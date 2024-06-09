package com.example.api.services.impl;

import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.helpers.Response;
import com.example.api.common.mappers.Mapper;
import com.example.api.common.messages.NotFoundMessage;
import com.example.api.domain.dtos.option.HandlerOptionResponse;
import com.example.api.domain.dtos.option.OptionRequest;
import com.example.api.domain.dtos.option.OptionResponse;
import com.example.api.domain.entities.OptionEntity;
import com.example.api.repositories.OptionRepository;
import com.example.api.repositories.ProductRepository;
import com.example.api.services.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final Mapper<OptionEntity, OptionResponse> mapper;
    private final Mapper<OptionEntity, HandlerOptionResponse> handlerMapper;

    @Override
    public Response<HandlerOptionResponse> save(String productId, OptionRequest request) {
        var product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND)
        );

        var option = OptionEntity
                .builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .product(product)
                .price(request.getPrice())
                .sale(request.getSale())
                .quantity(request.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();

        optionRepository.save(option);

        var result = handlerMapper.mapTo(option);

        return new Response<>(HttpStatus.CREATED.value(), result);
    }

    @Override
    public Response<List<OptionResponse>> findAll(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }

        var options = optionRepository.findAllByProductId(productId);

        var result = options.stream().map(mapper::mapTo).toList();

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<OptionResponse> findOne(String productId, UUID optionId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }

        var option = this.findOptionThrowExpedition(optionId);

        var result = mapper.mapTo(option);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override

    public Response<HandlerOptionResponse> update(String productId, UUID optionId,
                                      OptionRequest optionRequest) {

        if (!productRepository.existsById(productId)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }

        var option = this.findOptionThrowExpedition(optionId);

        option.setName(optionRequest.getName());
        option.setPrice(optionRequest.getPrice());
        option.setQuantity(optionRequest.getQuantity());
        option.setSale(optionRequest.getSale());
        option.setUpdatedAt(LocalDateTime.now());

        optionRepository.save(option);

        var result = handlerMapper.mapTo(option);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public void delete(String productId, UUID optionId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }

        var option = this.findOptionThrowExpedition(optionId);

        optionRepository.delete(option);
    }

    private OptionEntity findOptionThrowExpedition(UUID id) {
        return optionRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND)
        );
    }
}
