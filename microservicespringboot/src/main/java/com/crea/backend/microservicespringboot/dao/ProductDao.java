package com.crea.backend.microservicespringboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crea.backend.microservicespringboot.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    // public List<Product> findAll();

    public Product findById(int id);

    List<Product> findByNomLike(String nom);
    
    // @Query("SELECT id,nom,prix,prixAchat FROM Product WHERE prix > :prixLimit")
    List<Product> findByPrixGreaterThan(int prixLimit);

    // @Query("SELECT id,nom,prix,prixAchat,from Product WHERE")
}

