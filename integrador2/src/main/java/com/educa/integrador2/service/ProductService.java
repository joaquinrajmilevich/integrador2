package com.educa.integrador2.service;

import com.educa.integrador2.entity.Product;
import com.educa.integrador2.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductPrice(Long productId, double newPrice) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setPrice(newPrice);
            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }
    public Product addStock(Long productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setStock(product.getStock() + quantity);
            return productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }

    public Product removeStock(Long productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            int currentStock = product.getStock();
            if (currentStock >= quantity) {
                product.setStock(currentStock - quantity);
                return productRepository.save(product);
            } else {
                throw new RuntimeException("Insufficient stock for product with id: " + productId);
            }
        } else {
            throw new EntityNotFoundException("Product not found with id: " + productId);
        }
    }
    // Implement methods to add and remove stock as needed
}