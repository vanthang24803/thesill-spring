package com.example.api.services;

import com.example.api.common.helpers.Response;
import com.example.api.domain.entities.BillboardEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BillboardService {

    Response<List<BillboardEntity>> save(List<MultipartFile> billboards);

    Response<List<BillboardEntity>> findAll();

    void delete(String id);
}
