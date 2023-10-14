package com.crea.backend.microservicespringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crea.backend.microservicespringboot.dao.UserDao;

import com.crea.backend.microservicespringboot.model.Utilisateur;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    @Autowired

    private UserDao userDao;
    // @Autowired
    // private ProductDao productDao;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Utilisateur userToRegister) {
        // Check if the email exists in the database
        Utilisateur existingUser = userDao.findByEmail(userToRegister.getEmail());

        if (existingUser != null) {
            return new ResponseEntity<>("User with this email already exists", HttpStatus.BAD_REQUEST);
        }

        try {
            // Validate input and save the user to the database
            userDao.save(userToRegister);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("User registration failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur user) {
        // Find the user by email
        Utilisateur existingUser = userDao.findByEmail(user.getEmail());

        if (existingUser != null) {
            // Check if the password matches (you should use a password encoder)
            if (existingUser.getpasswords().equals(user.getpasswords())) {
                return ResponseEntity.ok(existingUser); // HTTP status code 200 and user data
            } else {
                // Authentication failed - incorrect password
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Authentication failed (incorrect password)"); // HTTP status code 401
            }
        } else {
            // Authentication failed - user not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed (user not found)"); // HTTP status code401

        }

    }
}
