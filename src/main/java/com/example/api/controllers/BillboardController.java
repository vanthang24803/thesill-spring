package com.example.api.controllers;

import com.example.api.common.helpers.Response;
import com.example.api.domain.entities.BillboardEntity;
import com.example.api.services.BillboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/billboards")
@RequiredArgsConstructor
public class BillboardController {
    private final BillboardService billboardService;

    @PostMapping
    public ResponseEntity<Response<List<BillboardEntity>>> upload(@RequestParam("billboards") List<MultipartFile> files) {
        return new ResponseEntity<>(billboardService.save(files), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response<List<BillboardEntity>>> findAll() {
        return ResponseEntity.ok(billboardService.findAll());
    }

    @DeleteMapping("{id}")

    public ResponseEntity<Void> remove(@PathVariable("id") String id) {
        billboardService.delete(id);
        return ResponseEntity.ok().build();
    }

}

