package dev.kei.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsListDto {
    private Long id;
    private String skuCode;
    private Float price;
    private Integer quantity;
}
