package com.cosmetics.shop.service;

import com.cosmetics.shop.entity.Seller;

import java.util.List;

public interface SellerService {

    Seller createSeller(Seller seller);

    Seller getSellerById(Long sellerId);

    List<Seller> getAllSellers();
}
