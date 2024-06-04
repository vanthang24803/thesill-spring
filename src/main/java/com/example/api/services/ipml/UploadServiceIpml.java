package com.example.api.services.ipml;

import com.cloudinary.Cloudinary;
import com.example.api.domain.dtos.cloudinary.CloudinaryResponse;
import com.example.api.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.FunctorException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadServiceIpml implements UploadService {

    private final Cloudinary cloudinary;

    @Override
    public CloudinaryResponse upload(MultipartFile multipartFile) throws IOException {
        final Map<?, ?> result = cloudinary.uploader().upload(multipartFile.getBytes(),
                Map.of("public_id", UUID.randomUUID().toString()));
        final String url = (String) result.get("url");
        final String publicId = (String) result.get("public_id");

        return CloudinaryResponse
                .builder()
                .publicId(publicId)
                .url(url)
                .build();
    }

    @Override
    public void delete(String publicId) {
        try {
            Map<?, ?> result = cloudinary.uploader().destroy(publicId, null);
            result.get("result");
        } catch (Exception exception) {
            throw new FunctorException("Delete failed!");
        }
    }
}
