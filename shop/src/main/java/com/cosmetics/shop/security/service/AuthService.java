package com.cosmetics.shop.security.service;

import com.cosmetics.shop.entity.Customer;
import com.cosmetics.shop.entity.Seller;
import com.cosmetics.shop.entity.User;
import com.cosmetics.shop.model.Role;
import com.cosmetics.shop.repository.UserRepository;
import com.cosmetics.shop.security.dto.AuthResponse;
import com.cosmetics.shop.security.dto.LoginRequest;
import com.cosmetics.shop.security.dto.RegisterCustomerRequest;
import com.cosmetics.shop.security.dto.RegisterSellerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    //Enregistrement Customer
    public AuthResponse registerCustomer(RegisterCustomerRequest request)
    {
        Customer c = new Customer();
        c.setRole(Role.CUSTOMER);
        c.setEmail(request.getEmail());
        c.setPassword(passwordEncoder.encode(request.getPassword()));
        c.setUsername(request.getUsername());

        userRepository.save(c);

        String token = jwtService.generateToken(c);
        return new AuthResponse(token,c.getRole().toString());
    }

    // Enregistrement Seller
    public AuthResponse registerSeller(RegisterSellerRequest request) {
        Seller seller = new Seller();
        seller.setUsername(request.getUsername());
        seller.setEmail(request.getEmail());
        seller.setPassword(passwordEncoder.encode(request.getPassword()));
        seller.setRole(Role.SELLER);
        seller.setStoreName(request.getStoreName());
        seller.setStoreAddress(request.getStoreAddress());

        userRepository.save(seller);

        String token = jwtService.generateToken(seller);
        return new AuthResponse(token,seller.getRole().toString());
    }

    // Login pour tous les utilisateurs
    public AuthResponse login(LoginRequest request) {
        // AuthenticationManager va vérifier email + password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Récupère l'utilisateur pour générer le token
        User user = userRepository.findByEmail(request.getEmail());
        String token = jwtService.generateToken(user);
        return new AuthResponse(token,user.getRole().toString());
    }

}
