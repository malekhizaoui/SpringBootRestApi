package com.crea.backend.microservicespringboot.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crea.backend.microservicespringboot.model.Utilisateur;


public interface UserDao extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
    Utilisateur findBypasswords(String passwords);
    Utilisateur findById(int id);
    @Query("SELECT DISTINCT u FROM Utilisateur u LEFT JOIN FETCH u.favoriteProducts")
    List<Utilisateur> findAllUsersWithFavoriteProducts();
    
}