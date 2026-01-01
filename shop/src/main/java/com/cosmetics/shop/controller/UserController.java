package com.cosmetics.shop.controller;

import com.cosmetics.shop.entity.User;
import com.cosmetics.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAll()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        userService.deleteUser(id);
    }
}
