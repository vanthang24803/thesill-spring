package com.example.api.services.impl;

import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.helpers.Response;
import com.example.api.common.mappers.Mapper;
import com.example.api.common.messages.NotFoundMessage;
import com.example.api.domain.dtos.color.ColorRequest;
import com.example.api.domain.dtos.color.ColorResponse;
import com.example.api.domain.dtos.color.CreateColorRequest;
import com.example.api.domain.dtos.color.RemoveColorRequest;
import com.example.api.domain.entities.ColorEntity;
import com.example.api.repositories.ColorRepository;
import com.example.api.repositories.OptionRepository;
import com.example.api.services.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    private final OptionRepository optionRepository;

    private final Mapper<ColorEntity, ColorResponse> mapper;

    @Override
    public Response<List<ColorResponse>> save(UUID optionId, CreateColorRequest requests) {
        var option = optionRepository.findById(optionId).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND)
        );

        List<ColorEntity> colors = new ArrayList<>();

        for (var request : requests.getColors()) {
            var color = ColorEntity
                    .builder()
                    .id(UUID.randomUUID())
                    .name(request.getName())
                    .value(request.getValue())
                    .option(option)
                    .quantity(request.getQuantity())
                    .createdAt(LocalDateTime.now())
                    .build();

            colorRepository.save(color);
            colors.add(color);
        }

        var totalQuantity = requests.getColors().stream().mapToLong(ColorRequest::getQuantity).sum();

        option.setQuantity(totalQuantity);

        optionRepository.save(option);

        var result = colors.stream().map(mapper::mapTo).toList();

        return new Response<>(HttpStatus.CREATED.value(), result);
    }

    @Override
    public Response<List<ColorResponse>> findAll(UUID optionId) {
        if (!optionRepository.existsById(optionId)) {
            throw new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND);
        }
        List<ColorEntity> colors = colorRepository.findAllByOptionId(optionId);
        var result = colors.stream().map(mapper::mapTo).toList();
        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<ColorResponse> findOne(UUID optionId, UUID colorId) {
        if (!optionRepository.existsById(optionId)) {
            throw new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND);
        }

        var color = this.findColorAndThrowEx(optionId);

        var result = mapper.mapTo(color);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public Response<ColorResponse> update(UUID optionId, UUID colorId, ColorRequest request) {
        if (!optionRepository.existsById(optionId)) {
            throw new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND);
        }

        var color = this.findColorAndThrowEx(optionId);

        color.setName(request.getName());
        color.setValue(request.getValue());
        color.setQuantity(request.getQuantity());
        color.setUpdatedAt(LocalDateTime.now());

        colorRepository.save(color);

        var result = mapper.mapTo(color);

        return new Response<>(HttpStatus.OK.value(), result);
    }

    @Override
    public void remove(UUID optionId, UUID colorId) {
        if (!optionRepository.existsById(optionId)) {
            throw new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND);
        }

        var color = this.findColorAndThrowEx(optionId);

        colorRepository.delete(color);
    }

    @Override
    public void removeMany(UUID optionId, RemoveColorRequest requests) {
        if (!optionRepository.existsById(optionId)) {
            throw new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND);
        }

        for (var request : requests.getColors()) {
            var color = this.findColorAndThrowEx(request.getId());
            colorRepository.delete(color);
        }
    }

    @Override
    public void clear(UUID optionId) {
        if (!optionRepository.existsById(optionId)) {
            throw new NotFoundException(NotFoundMessage.OPTION_NOT_FOUND);
        }

        var color = colorRepository.findAllByOptionId(optionId);

        colorRepository.deleteAll(color);
    }

    private ColorEntity findColorAndThrowEx(UUID id) {
        return colorRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.COLOR_NOT_FOUND)
        );
    }
}
