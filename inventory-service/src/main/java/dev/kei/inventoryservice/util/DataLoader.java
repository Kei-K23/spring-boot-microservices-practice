package dev.kei.inventoryservice.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.kei.inventoryservice.model.Inventory;
import dev.kei.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Inventory inventory1 = new Inventory();
        inventory1.setSkuCode("product-1");
        inventory1.setQuantity(10);

        Inventory inventory2 = new Inventory();
        inventory2.setSkuCode("product-2");
        inventory2.setQuantity(2);

        Inventory inventory3 = new Inventory();
        inventory3.setSkuCode("product-3");
        inventory3.setQuantity(40);

        inventoryRepository.save(inventory1);
        inventoryRepository.save(inventory2);
        inventoryRepository.save(inventory3);
    }

}
