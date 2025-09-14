package com.vedant.RateLimiter.TokenBucket.interceptor;

import com.vedant.RateLimiter.TokenBucket.service.TokenBucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class TokenBucketInterceptor extends OncePerRequestFilter {

    private final TokenBucket tokenBucket = new TokenBucket(5,3);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(tokenBucket.tryConsume()){
            System.out.println("Interceptor called");
            System.out.println(tokenBucket.getTokens());
            filterChain.doFilter(request,response);
        }
        else{
            response.setStatus(429); // Too many requests
            response.getWriter().write("Rate limit exceeded. Try again later.\n");
        }
    }
}
