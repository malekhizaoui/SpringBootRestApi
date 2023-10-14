package com.crea.backend.microservicespringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;

import com.crea.backend.microservicespringboot.dao.ProductDao;
import com.crea.backend.microservicespringboot.dao.UserDao;
import com.crea.backend.microservicespringboot.model.Product;
import com.crea.backend.microservicespringboot.model.Utilisateur;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/favouriteProduct")
public class FavouriteProduct {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @PostMapping("/addFavoriteProduct/{userId}/{productId}")
    public ResponseEntity<?> addFavoriteProduct(@PathVariable Long userId, @PathVariable Integer productId) {
        // Retrieve the user and product based on their IDs
        Utilisateur user = userDao.findById(userId).orElse(null);
        Product product = productDao.findById(productId).orElse(null);

        if (user != null && product != null) {
            // Check if the product is not already in the user's favorites
            if (!user.getFavoriteProducts().contains(product)) {
                // Add the product to the user's favorites
                user.getFavoriteProducts().add(product);
                // Save the updated user, which will also update the join table
                userDao.save(user);
                return ResponseEntity.ok("Product added to favorites successfully");
            } else {
                return ResponseEntity.badRequest().body("Product is already in favorites");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("product failed to add touser (user not found)"); // HTTP
        }
    }

    @GetMapping("/userFavoriteProducts/{userId}")
    public ResponseEntity<?> getUserFavoriteProducts(@PathVariable Long userId) {
        Utilisateur user = userDao.findById(userId).orElse(null);

        if (user != null) {
            Set<Product> favoriteProducts = user.getFavoriteProducts();
            return ResponseEntity.ok(favoriteProducts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allUsersWithFavorites")
    public List<Utilisateur> getAllUsersWithFavoriteProducts() {
        return userDao.findAllUsersWithFavoriteProducts();
    }
}
