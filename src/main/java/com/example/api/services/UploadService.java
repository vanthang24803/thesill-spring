package com.example.api.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String uploadFile(MultipartFile multipartFile) throws IOException;
}
