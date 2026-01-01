package com.cosmetics.shop.controller;

import com.cosmetics.shop.security.dto.AuthResponse;
import com.cosmetics.shop.security.dto.LoginRequest;
import com.cosmetics.shop.security.dto.RegisterCustomerRequest;
import com.cosmetics.shop.security.dto.RegisterSellerRequest;
import com.cosmetics.shop.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/customer")
    public void registerCustomer(@RequestBody RegisterCustomerRequest request) {
        authService.registerCustomer(request);
    }

    @PostMapping("/register/seller")
    public void registerSeller(@RequestBody RegisterSellerRequest request) {
        authService.registerSeller(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
