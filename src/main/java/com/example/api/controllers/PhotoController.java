package com.example.api.controllers;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.photo.ListPhotoRequest;
import com.example.api.domain.entities.PhotoEntity;
import com.example.api.services.PhotoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Photo", description = "Endpoints for photo manager")
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping("{productId}/photos")
    public ResponseEntity<Response<List<PhotoEntity>>> create(
            @PathVariable("productId") String id,
            @RequestParam("photos") List<MultipartFile> files
    ) {
        return new ResponseEntity<>(photoService.save(id, files), HttpStatus.CREATED);
    }

    @GetMapping("{productId}/photos")
    public ResponseEntity<Response<List<PhotoEntity>>> findAll(
            @PathVariable("productId") String id
    ) {
        return ResponseEntity.ok(photoService.findAll(id));
    }

    @DeleteMapping("{productId}/photos/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("productId") String productId,
            @PathVariable("id") String id
    ) {
        photoService.remove(productId, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{productId}/photos/remove")
    public ResponseEntity<Void> deleteMany(
            @PathVariable("productId") String productId,
            @RequestBody @Valid ListPhotoRequest listPhotoRequest
    ) {
        photoService.removeMany(productId, listPhotoRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{productId}/photos/clear")

    public ResponseEntity<Void> clear(
            @PathVariable("productId") String productId
    ) {
        photoService.clear(productId);
        return ResponseEntity.ok().build();
    }

}
