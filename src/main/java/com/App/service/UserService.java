package com.App.service;

import com.App.config.JwtUtils;
import com.App.model.*;
import com.App.repository.UserRepository;
import com.App.response.MyResponse;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    public MyResponse saveUser(Register register) {

        // Check if password and confirm password match
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            MyResponse.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");

            return MyResponse.builder().statusText("Registration failed").build();

        } else {


            var user = User.builder().name(register.getName()).email(register.getEmail()).password(passwordEncoder.encode(register.getPassword())).confirmPassword(passwordEncoder.encode(register.getConfirmPassword())).role(ERole.BUYER).userId(register.getUserId()).build();
            userRepository.save(user);
            return MyResponse.builder().statusText("User Added Successfully").build();
        }


    }

    public User updateUserByUserId(User user) {

        var user1 = User.builder().name(user.getName()).email(user.getEmail()).password(passwordEncoder.encode(user.getPassword())).confirmPassword(passwordEncoder.encode(user.getConfirmPassword())).role(user.getRole()).userId(user.getUserId()).build();
        return userRepository.save(user);
    }


    public String deleteUserById(int userId) {
        userRepository.deleteById(userId);
        return "deleted " + userId;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(int userId) {
        return userRepository.findByUserId(userId);

    }


    public ResponseEntity<MyResponse> authenticate(Login login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MyResponse.builder().statusText("Invalid username or password").build());
        }

        Optional<User> userDetails = userRepository.findByEmail(login.getEmail());
        String token = jwtUtils.generateToken(userDetails.get());
        String role = userDetails.get().getAuthorities().stream().findFirst().get().getAuthority();
//        User user = userRepository.getUserByEmail(login.getEmail());
        return ResponseEntity.ok(MyResponse.builder().token(token).user(userDetails.get()).role(role).statusText("Login Successful").build());
    }


}
