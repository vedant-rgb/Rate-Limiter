package com.vedant.RateLimiter.LeakyBucket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leakyBucket")
public class LeakyBucketController {
    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World! This is Leaky Bucket Rate Limiter\n";
    }
}
