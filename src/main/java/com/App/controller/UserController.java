package com.App.controller;


import com.App.model.Login;
import com.App.model.Products;
import com.App.model.Register;
import com.App.model.User;
import com.App.response.MyResponse;
import com.App.service.ProductService;
import com.App.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {


    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;


    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<MyResponse> addUser(@RequestBody Register register) {


        return ResponseEntity.ok().body(service.saveUser(register));
    }

    @PostMapping("/login")
    public ResponseEntity<MyResponse> login(@RequestBody Login login) {
        return service.authenticate(login);
    }



    @GetMapping("/getAllProducts")
    public List getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getProductById/{id}")
    public Optional<Products> getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }


    @GetMapping("/getProductByCategory/{category}")
    public List<Products> getProductByCategory(@PathVariable  String category){
        return productService.getProductsByCategory(category);
    }

}
