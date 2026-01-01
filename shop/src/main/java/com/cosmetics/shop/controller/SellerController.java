package com.cosmetics.shop.controller;

import com.cosmetics.shop.entity.Seller;
import com.cosmetics.shop.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping
    public Seller createSeller(@RequestBody Seller seller)
    {
        return sellerService.createSeller(seller);
    }

    @GetMapping("/{id}")
    public Seller getSellerById(@PathVariable Long id)
    {
        return sellerService.getSellerById(id);
    }

    @GetMapping
    public List<Seller> getAllSellers()
    {
        return sellerService.getAllSellers();
    }
}
