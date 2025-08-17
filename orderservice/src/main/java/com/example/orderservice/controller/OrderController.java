package com.example.orderservice.controller;

import com.example.orderservice.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @PostMapping(path = "/orders")
    public ResponseEntity<String> handleProductUpdate(@RequestBody Product product) {
        System.out.println("Received product update event for product: " + product.getName());
        // Order processing logic here

        return ResponseEntity.ok("OrderService processed product update");
    }

    @GetMapping("/health")
    public String health() {
        return "OrderService is running";
    }
}