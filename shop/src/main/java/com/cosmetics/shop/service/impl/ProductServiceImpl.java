package com.cosmetics.shop.service.impl;

import com.cosmetics.shop.entity.Product;
import com.cosmetics.shop.repository.ProductRepository;
import com.cosmetics.shop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product p = productRepository.findById(productId).orElse(null);
        p.setName(product.getName());
        p.setBrand(product.getBrand());
        p.setStock(product.getStock());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        p.setImageUrl(product.getImageUrl());
        return productRepository.save(p);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Override
    public void updateStock(Long productId, int quantity) {
        Product p = productRepository.findById(productId).orElse(null);
        p.setStock(p.getStock()-quantity);
        productRepository.save(p);
    }
}
