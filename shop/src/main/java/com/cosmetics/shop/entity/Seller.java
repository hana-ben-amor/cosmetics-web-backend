package com.cosmetics.shop.entity;

import com.cosmetics.shop.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Seller extends User{
    private String storeName;
    private String storeAddress;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    public Seller(String username, String password, String email, Role role, LocalDateTime createdAt) {
        super(username, password, email, role, createdAt);
    }

    public Seller() {
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
