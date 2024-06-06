package com.example.api.domain.dtos.photo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPhotoRequest {
    List<PhotoRequest> photos;
}



