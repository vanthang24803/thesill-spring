package com.example.api.services;

import com.example.api.domain.dtos.cloudinary.CloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    CloudinaryResponse upload(MultipartFile multipartFile) throws IOException;

    void delete(String publicId);
}
