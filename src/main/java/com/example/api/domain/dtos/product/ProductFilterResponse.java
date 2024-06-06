package com.example.api.domain.dtos.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterResponse {
    private int page;
    private int limit;
    private int totalPage;
    private int totalProduct;
    private List<ProductResponse> result;
}
