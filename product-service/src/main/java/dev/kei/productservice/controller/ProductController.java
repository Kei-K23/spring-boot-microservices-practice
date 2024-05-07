package dev.kei.productservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.kei.productservice.dto.ProductRequest;
import dev.kei.productservice.dto.ProductResponse;
import dev.kei.productservice.service.ProductService;
import dev.kei.productservice.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductResponse> getProductById(@PathVariable(name = "id") String id) {

        Optional<ProductResponse> product = productService.getProductById(id);
        if (!product.isPresent()) {
            String message = "Product with id `" + id + "` not found";
            System.out.println(message);
            throw new ResourceNotFoundException(message);
        }
        return product;
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {

        Optional<ProductResponse> product = productService.getProductById(id);
        if (!product.isPresent()) {
            String message = "Product with id `" + id + "` not found";
            throw new ResourceNotFoundException(message);
        }

        return productService.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {

        Optional<ProductResponse> product = productService.getProductById(id);
        if (!product.isPresent()) {
            String message = "Product with id `" + id + "` not found";
            throw new ResourceNotFoundException(message);
        }

        productService.deleteProduct(id);
    }
}
