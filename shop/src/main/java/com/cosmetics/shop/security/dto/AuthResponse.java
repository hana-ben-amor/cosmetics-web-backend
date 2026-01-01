package com.cosmetics.shop.security.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String role;

    // Constructeur par défaut nécessaire pour Jackson
    public AuthResponse() {
    }

    // Constructeur pratique pour créer l'objet
    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
