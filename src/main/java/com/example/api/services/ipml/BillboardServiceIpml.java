package com.example.api.services.ipml;

import com.example.api.common.exceptions.NotFoundException;
import com.example.api.domain.dtos.message.Response;
import com.example.api.domain.entities.BillboardEntity;
import com.example.api.repositories.BillboardRepository;
import com.example.api.services.BillboardService;
import com.example.api.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillboardServiceIpml implements BillboardService {
    private final UploadService uploadService;
    private final BillboardRepository billboardRepository;

    @Override
    public Response<List<BillboardEntity>> save(List<MultipartFile> billboards) {
        List<BillboardEntity> response = new ArrayList<>();

        for (MultipartFile billboard : billboards) {
            try {
                var result = uploadService.upload(billboard);

                var newBillboard = BillboardEntity.builder()
                        .id(result.getPublicId())
                        .url(result.getUrl())
                        .createdAt(LocalDateTime.now())
                        .build();

                billboardRepository.save(newBillboard);
                response.add(newBillboard);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Response<>(HttpStatus.CREATED.value(), response);
    }

    @Override
    public Response<List<BillboardEntity>> findAll() {
        List<BillboardEntity> billboards = billboardRepository.findAll();
        return new Response<>(HttpStatus.OK.value(), billboards);
    }

    @Override
    public void delete(String id) {

        var billboard = billboardRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Billboard not found!")
        );

        uploadService.delete(id);

        billboardRepository.delete(billboard);

    }
}
