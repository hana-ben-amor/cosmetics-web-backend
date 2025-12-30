package com.cosmetics.shop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer extends User{
    private String address;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
