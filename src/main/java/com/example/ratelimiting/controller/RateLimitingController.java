package com.example.ratelimiting.controller;

import com.example.ratelimiting.service.RateLimitingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rate-limiting")
public class RateLimitingController {

    private final RateLimitingService rateLimitingService;

    public RateLimitingController(RateLimitingService rateLimitingService) {
        this.rateLimitingService = rateLimitingService;
    }

    @GetMapping("/resource")
    public String getResource() {
        String apiKey = "test-api-key"; // Retrieve API key from request headers or JWT token
        if (rateLimitingService.allowRequest(apiKey)) {
            return "Resource api accessed successfully";
        } else {
            return "Rate limit exceeded. Please Try again later.";
        }
    }
}
