package com.cosmetics.shop.service.impl;

import com.cosmetics.shop.entity.Seller;
import com.cosmetics.shop.model.Role;
import com.cosmetics.shop.repository.SellerRepository;
import com.cosmetics.shop.service.SellerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller createSeller(Seller seller) {
        seller.setRole(Role.SELLER);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller getSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId).orElseThrow(
                ()->{
                    throw new RuntimeException("Seller not found");
                }
        );
    }

    @Override
    public List<Seller> getAllSellers() {
        return List.of();
    }
}
