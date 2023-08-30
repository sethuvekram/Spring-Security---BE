package com.App.controller;

import com.App.model.Products;
import com.App.model.User;
import com.App.service.ProductService;
import com.App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {

    private final UserService service;
    private final ProductService productService;


    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        System.out.println("naveen is smart boy");
        return service.updateUserByUserId(user);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUserById(@PathVariable int userId) {
        return service.deleteUserById(userId);

    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/getUserById/{userId}")
    public Optional<User> getUserById(@PathVariable int userId) {
        return service.getUserById(userId);
    }


    @PostMapping("/addProduct")
    public Products add(@RequestBody Map Products) {
        return productService.addProduct(Products);
    }


    @PutMapping("/updateProduct")
    public Products update(@RequestBody Products Products) {
        return productService.updateProduct(Products);
    }


    @GetMapping("/getAllProducts")
    public List getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/getProductById/{id}")
    public Optional<Products> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/getProductByCategory/{category}")
    public List<Products> getProductByCategory(@PathVariable  String category){
        return productService.getProductsByCategory(category);
    }
}




