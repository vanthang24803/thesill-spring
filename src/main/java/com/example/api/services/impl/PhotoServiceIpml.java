package com.example.api.services.impl;

import com.example.api.common.exceptions.NotFoundException;
import com.example.api.common.messages.NotFoundMessage;
import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.photo.ListPhotoRequest;
import com.example.api.domain.dtos.photo.PhotoRequest;
import com.example.api.domain.entities.PhotoEntity;
import com.example.api.repositories.PhotoRepository;
import com.example.api.repositories.ProductRepository;
import com.example.api.services.PhotoService;
import com.example.api.services.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoServiceIpml implements PhotoService {

    private final ProductRepository productRepository;
    private final PhotoRepository photoRepository;
    private final UploadService uploadService;

    @Override
    public Response<List<PhotoEntity>> save(String id, List<MultipartFile> files) {
        var product = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND)
        );

        List<PhotoEntity> photos = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                var result = uploadService.upload(file);

                var newPhoto = PhotoEntity
                        .builder()
                        .id(result.getPublicId())
                        .url(result.getUrl())
                        .createdAt(LocalDateTime.now())
                        .product(product)
                        .build();

                photoRepository.save(newPhoto);
                photos.add(newPhoto);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        product.setPhotos(photos);
        return new Response<>(HttpStatus.CREATED.value(), photos);
    }

    @Override
    public Response<List<PhotoEntity>> findAll(String id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }
        var photos = photoRepository.findAllByProductId(id);
        return new Response<>(HttpStatus.OK.value(), photos);
    }

    @Override
    public void remove(String productId, String id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }

        var photo = photoRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundMessage.CATEGORY_NOT_FOUND)
        );

        uploadService.delete(photo.getId());
        photoRepository.delete(photo);
    }

    @Override
    public void removeMany(String id, ListPhotoRequest request) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }

        for (PhotoRequest photo : request.getPhotos()) {
            var existingPhoto = photoRepository.findById(photo.getId()).orElseThrow(
                    () -> new NotFoundException(NotFoundMessage.CATEGORY_NOT_FOUND)
            );

            uploadService.delete(existingPhoto.getId());
            photoRepository.delete(existingPhoto);
        }
    }

    @Override
    public void clear(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException(NotFoundMessage.PRODUCT_NOT_FOUND);
        }

        var photos = photoRepository.findAllByProductId(productId);

        for (PhotoEntity photo : photos) {
            uploadService.delete(photo.getId());
        }

        photoRepository.deleteAll(photos);

    }
}
