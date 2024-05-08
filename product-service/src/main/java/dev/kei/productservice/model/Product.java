package dev.kei.productservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
@Getter
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
