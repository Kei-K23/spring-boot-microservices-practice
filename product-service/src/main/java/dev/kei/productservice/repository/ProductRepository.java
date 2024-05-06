package dev.kei.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kei.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
