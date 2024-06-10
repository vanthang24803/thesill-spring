package com.example.api.controllers;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.color.ColorRequest;
import com.example.api.domain.dtos.color.ColorResponse;
import com.example.api.domain.dtos.color.CreateColorRequest;
import com.example.api.domain.dtos.color.RemoveColorRequest;
import com.example.api.services.ColorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/options")
@RequiredArgsConstructor
@Tag(name = "Colors", description = "Endpoints for color manager")
public class ColorController {
    private final ColorService colorService;

    @GetMapping("{optionId}/colors")
    public ResponseEntity<Response<List<ColorResponse>>> findAll(
            @PathVariable("optionId") UUID optionId
    ) {
        return ResponseEntity.ok(colorService.findAll(optionId));
    }

    @PostMapping("{optionId}/colors")
    public ResponseEntity<Response<List<ColorResponse>>> create(
            @PathVariable("optionId") UUID optionId,
            @RequestBody @Valid CreateColorRequest request
    ) {
        return new ResponseEntity<>(colorService.save(optionId, request), HttpStatus.CREATED);
    }

    @GetMapping("{optionId}/colors/{colorId}")
    public ResponseEntity<Response<ColorResponse>> findOne(
            @PathVariable("optionId") UUID optionId,
            @PathVariable("colorId") UUID colorId
    ) {
        return ResponseEntity.ok(colorService.findOne(optionId, colorId));
    }

    @PutMapping("{optionId}/colors/{colorId}")
    public ResponseEntity<Response<ColorResponse>> update(
            @PathVariable("optionId") UUID optionId,
            @PathVariable("colorId") UUID colorId,
            @RequestBody @Valid ColorRequest request
    ) {
        return ResponseEntity.ok(colorService.update(optionId, colorId, request));
    }

    @DeleteMapping("{optionId}/colors/{colorId}")
    public ResponseEntity<Void> delete(
            @PathVariable("optionId") UUID optionId,
            @PathVariable("colorId") UUID colorId
    ) {
        colorService.remove(optionId, colorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{optionId}/colors/remove")
    public ResponseEntity<Void> removeMany(
            @PathVariable("optionId") UUID optionId,
            @RequestBody @Valid RemoveColorRequest request
    ) {
        colorService.removeMany(optionId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{optionId}/colors/clear")
    public ResponseEntity<Void> clear(
            @PathVariable("optionId") UUID optionId
    ) {
        colorService.clear(optionId);
        return ResponseEntity.ok().build();
    }
}
