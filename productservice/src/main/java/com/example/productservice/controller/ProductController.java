package com.example.productservice.controller;

import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    private static final String DAPR_PUBSUB_URL = "http://localhost:3500/v1.0/publish/pubsub/product-updates";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product) {
        // Save product logic can be added here

        // Publish event to Dapr pub/sub
        restTemplate.postForObject(DAPR_PUBSUB_URL, product, String.class);
        return "Product added and event published!";
    }

    @GetMapping("/health")
    public String health() {
        return "ProductService is running";
    }
}
