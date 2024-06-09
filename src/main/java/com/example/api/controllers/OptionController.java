package com.example.api.controllers;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.option.HandlerOptionResponse;
import com.example.api.domain.dtos.option.OptionRequest;
import com.example.api.domain.dtos.option.OptionResponse;
import com.example.api.services.OptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
@Tag(name = "Options", description = "Endpoints for option manager")
@RequiredArgsConstructor
@Slf4j
public class OptionController {
    private final OptionService optionService;

    @GetMapping("{productId}/options")
    public ResponseEntity<Response<List<OptionResponse>>> findAll(
            @PathVariable("productId") String productId
    ) {
        return ResponseEntity.ok(optionService.findAll(productId));
    }

    @PostMapping("{productId}/options")
    public ResponseEntity<Response<HandlerOptionResponse>> create(
            @PathVariable("productId") String productId,
            @RequestBody @Valid OptionRequest request
    ) {
        return new ResponseEntity<>(optionService.save(productId, request), HttpStatus.CREATED);
    }

    @GetMapping("{productId}/options/{optionId}")
    public ResponseEntity<Response<OptionResponse>> findOne(
            @PathVariable("productId") String productId,
            @PathVariable("optionId") UUID optionId
    ) {
        return ResponseEntity.ok(optionService.findOne(productId, optionId));
    }

    @PutMapping("{productId}/options/{optionId}")
    public ResponseEntity<Response<HandlerOptionResponse>> update(
            @PathVariable("productId") String productId,
            @PathVariable("optionId") UUID optionId,
            @RequestBody @Valid OptionRequest request
    ) {
        return ResponseEntity.ok(optionService.update(productId, optionId, request));
    }

    @DeleteMapping("{productId}/options/{optionId}")
    public ResponseEntity<Void> remove(
            @PathVariable("productId") String productId,
            @PathVariable("optionId") UUID optionId
    ) {
        optionService.delete(productId, optionId);
        return ResponseEntity.ok().build();
    }
}
