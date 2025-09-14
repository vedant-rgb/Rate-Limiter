package com.vedant.RateLimiter.LeakyBucket.interceptor;

import com.vedant.RateLimiter.LeakyBucket.services.LeakyBucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LeakyBucketInterceptor implements HandlerInterceptor {

    private final LeakyBucket leakyBucket;

    public LeakyBucketInterceptor(LeakyBucket leakyBucket) {
        this.leakyBucket = leakyBucket;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(leakyBucket.allowed()){
            return true; // allow the request to proceed
        } else {
            response.setStatus(429); // Too Many Requests
            response.getWriter().write("Rate limit exceeded from leaky bucket. Try again later.\n");
            return false; // block the request
        }
    }
}
