package com.cosmetics.shop.service;

import com.cosmetics.shop.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    void deleteProduct(Long productId);

    Product updateProduct(Long productId,Product product);

    Product getProductById(Long productId);

    Product createProduct(Product product);

    List<Product> getProductsBySeller(Long sellerId);

    void updateStock(Long productId,int quantity);

}
