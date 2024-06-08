package com.example.api.controllers;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.tag.TagRequest;
import com.example.api.domain.entities.TagEntity;
import com.example.api.repositories.TagService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tag")
@RequiredArgsConstructor
@Tag(name = "Tag name", description = "Endpoints for manager tag name product")
public class TagController {
    private final TagService tagService;

    @GetMapping()
    public ResponseEntity<Response<List<TagEntity>>> findAll() {
        return ResponseEntity.ok(tagService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Response<TagEntity>> create(
            @RequestBody @Valid TagRequest tagRequest
    ) {
        return new ResponseEntity<>(tagService.save(tagRequest), HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<Response<TagEntity>> findOne(
            @PathVariable("id") UUID id
    ) {
        return ResponseEntity.ok(tagService.findOne(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<TagEntity>> update(
            @PathVariable("id") UUID id,
            @RequestBody @Valid TagRequest tagRequest
    ) {
        return ResponseEntity.ok(tagService.update(id, tagRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") UUID id
    ) {
        tagService.remove(id);
        return ResponseEntity.ok().build();
    }

}
