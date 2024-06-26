package com.ted.milanopizza.controller;

import com.ted.milanopizza.model.Product;
import com.ted.milanopizza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // get all products
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        try 
        {
            List<Product> productList = new ArrayList<>();
            productRepository.findAll().forEach(productList::add);

            if (productList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(productList, HttpStatus.OK);

        } 
        catch (Exception e) 
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        Product productObj = productRepository.save(product);

        return new ResponseEntity<>(productObj, HttpStatus.OK);
    }

    @PostMapping("product/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product newProduct)
    {
        Optional<Product> oldProduct = productRepository.findById(id);

        if(oldProduct.isPresent())
        {
            Product updatedProduct = oldProduct.get();
            if(newProduct.getPrice() != 0)
            {
                updatedProduct.setPrice(newProduct.getPrice());
            }

            if(newProduct.getDiscount() != 0) {
                updatedProduct.setDiscount(newProduct.getDiscount());
            }

            if(newProduct.getName() != null) {
                updatedProduct.setName(newProduct.getName());
            }

            if(newProduct.getImageUrl() != null) {
                updatedProduct.setImageUrl(newProduct.getImageUrl());
            }
            //
            Product productObj = productRepository.save(updatedProduct);
            return new ResponseEntity<>(productObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
    @DeleteMapping("product/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable int id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
