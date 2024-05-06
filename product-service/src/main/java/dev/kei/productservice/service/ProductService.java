package dev.kei.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.kei.productservice.dto.ProductRequest;
import dev.kei.productservice.dto.ProductResponse;
import dev.kei.productservice.model.Product;
import dev.kei.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product {} is saved.", product.getId());

        return mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public Optional<ProductResponse> getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::mapToProductResponse);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product product = Product.builder().id(id)
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product {} is updated.", product.getId());

        return mapToProductResponse(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
        log.info("Product {} is updated.", id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName())
                .description(product.getDescription()).price(product.getPrice()).build();
    }
}
