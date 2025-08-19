package com.example.productservice.controller;

import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${dapr.pubsub.url}")
    private String daprPubsubUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product) {
        logger.info("Received request to add product: {}", product);
        // Save product logic can be added here

        logger.info("Publishing product event to Dapr at {}", daprPubsubUrl);
        restTemplate.postForObject(daprPubsubUrl, product, String.class);
        logger.info("Product event published successfully");
        return "Product added and event published!";
    }

    @GetMapping("/health")
    public String health() {
        logger.debug("Health check endpoint called");
        return "ProductService is running";
    }
}