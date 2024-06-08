package com.example.api.common.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuery {
    private int page = 1;
    private int limit = 20;
    private String order = "asc";
}
