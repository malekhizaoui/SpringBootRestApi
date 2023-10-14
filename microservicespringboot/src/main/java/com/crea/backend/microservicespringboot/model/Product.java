package com.crea.backend.microservicespringboot.model;

// import jakarta.persistence.Column;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private int prix;
  
    @Column(name="PRIXACHAT")
    private int prixAchat;

       @ManyToMany(mappedBy = "favoriteProducts")
    private Set<Utilisateur> favoritedBy;
    // constructeur par défaut

    public Product() {
        
    }

    // constructeur pour nos tests

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Product(int id, String nom, int prix, int prixAchat) {
        this.id=id;
        this.nom=nom;
        this.prix=prix;
        this.prixAchat=prixAchat;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom=nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix=prix;
    }
    
    @Override
    public String toString(){  
        return "Product{"+
        "id=" + id + 
        ", nom='"+ nom + '\'' + 
        ", prix=" + prix+ '}';
    }
    
}