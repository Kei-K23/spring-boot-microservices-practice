package dev.kei.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private double price;
}
