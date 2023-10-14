package com.crea.backend.microservicespringboot.controller;

// import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crea.backend.microservicespringboot.dao.ProductDao;
import com.crea.backend.microservicespringboot.model.Product;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    // GET Method
    @RequestMapping(value = "/produits", method = RequestMethod.GET)
    public List<Product> listeProduits() {
        return productDao.findAll();
    }
    // POST Method 1

    @RequestMapping(value = "/addProduit", method = RequestMethod.POST)
    public void ajouterProduit(@RequestBody Product product) {
        try {
            productDao.save(product);
            // return product;
        } catch (Exception e) {
            throw e;
        }

    }

    @GetMapping(value = "/displayProduct/{id}")
    public String displayProduct(@PathVariable int id) {
        Product p = new Product(id, "iphone", 185, 200);
        return p.getNom();
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id) {
        Boolean isExistedproduct = productDao.existsById(id);
        System.out.println(isExistedproduct);
        if (isExistedproduct) {
            // Product productDeleted =productDao.findById(id);
            productDao.deleteById(id);
        }
        System.out.println("Product is not in the table");
    }

    @GetMapping(value = "/product/{name}")
    public List<Product> searchProducts(@PathVariable String name) {
        return productDao.findByNomLike("%" + name + "%");
    }

    @RequestMapping(value = "/productByTheHighestPrice/{price}", method = RequestMethod.GET)
    public List<Product> productHighPrice(@PathVariable int price) {
        return productDao.findByPrixGreaterThan(price);
    }

    @PutMapping(value="/productUpdate/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id ,@RequestBody  Product updatedProduct){
        Product isExistedproduct = productDao.findById(id);
        if (isExistedproduct!=null) {
            // Product productDeleted =productDao.findById(id);
            isExistedproduct.setNom(updatedProduct.getNom());
            isExistedproduct.setPrix(updatedProduct.getPrix());


            productDao.save(isExistedproduct);
                
            return ResponseEntity.ok("Product updated successfully");

        }
        return ResponseEntity.notFound().build();

    }

}
