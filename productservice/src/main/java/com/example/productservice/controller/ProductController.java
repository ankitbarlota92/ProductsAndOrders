package com.example.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.productservice.model.Product;

@RestController
public class ProductController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CHANNEL = "product-updates";

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product) {
        // Save product logic can be added here

        // Publish event to Redis channel
        redisTemplate.convertAndSend(CHANNEL, product);
        return "Product added and event published to Redis!";
    }

    @GetMapping("/health")
    public String health() {
        return "ProductService is running";
    }
}
