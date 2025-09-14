package com.vedant.RateLimiter.TokenBucket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokenBucket")
public class HelloWorld {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World! This is Token Bucket Rate Limiter.\n";
    }

}
