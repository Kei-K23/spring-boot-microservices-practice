package dev.kei.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.kei.inventoryservice.dto.InventoryResponse;
import dev.kei.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking inventory");

        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory -> InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build())
                .toList();
    }
}
