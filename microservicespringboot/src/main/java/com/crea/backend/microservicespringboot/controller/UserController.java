package com.crea.backend.microservicespringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.crea.backend.microservicespringboot.dao.UserDao;
import com.crea.backend.microservicespringboot.model.Utilisateur;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/allUsers")
    public List<Utilisateur> getAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        Utilisateur user = userDao.findById(userId).orElse(null);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody Utilisateur updatedUser) {
        Utilisateur user = userDao.findById(userId).orElse(null);

        if (user != null) {
            // Update the user's attributes with the new value
            user.setEmail(updatedUser.getEmail());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setpasswords(updatedUser.getpasswords());
            // Save the updated user to the database
            userDao.save(user);

            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Utilisateur user = userDao.findById(userId).orElse(null);

        if (user != null) {
            // Delete the user from the database
            userDao.delete(user);

            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
