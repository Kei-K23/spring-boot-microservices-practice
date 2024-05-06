package dev.kei.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.kei.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}