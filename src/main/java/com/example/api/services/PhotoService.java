package com.example.api.services;

import com.example.api.common.helpers.Response;
import com.example.api.domain.dtos.photo.ListPhotoRequest;
import com.example.api.domain.entities.PhotoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    Response<List<PhotoEntity>> save(String id, List<MultipartFile> files);

    Response<List<PhotoEntity>> findAll(String id);

    void remove(String productId, String id);

    void removeMany(String id, ListPhotoRequest request);

    void clear(String productId);
}
