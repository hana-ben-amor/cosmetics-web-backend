package com.cosmetics.shop.controller;

import com.cosmetics.shop.entity.Product;
import com.cosmetics.shop.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAll()
    {
        return productRepository.findAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product)
    {
        return productRepository.save(product);
    }
}
