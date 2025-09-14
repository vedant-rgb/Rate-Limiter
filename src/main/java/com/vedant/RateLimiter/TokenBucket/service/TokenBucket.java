package com.vedant.RateLimiter.TokenBucket.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TokenBucket {


    private final int capacity;

    private final int refillRate;
    private AtomicInteger tokens;
    private long lastRefillTimestamp;



    public TokenBucket(@Value("${rate.limiter.capacity}") int capacity,
                       @Value("${rate.limiter.refillRate}")int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = new AtomicInteger(capacity);
        this.lastRefillTimestamp = System.currentTimeMillis();
    }


    public boolean tryConsume(){
        refill(); // Refill the bucket for each request
        if(tokens.get()>0){
            tokens.decrementAndGet(); // consume 1 token from bucket
            return true;
        }
        return false;
    }
    private void refill(){
        long now = System.currentTimeMillis();
        long elapsedTime = (now - lastRefillTimestamp)/1000;

        if(elapsedTime>0){
            int tokensToAdd  = (int)(elapsedTime*refillRate); // tokens to add i.e refillRate * time elapsed after last fill
            int newTokenCount = Math.min(capacity, tokens.get() + tokensToAdd);
            tokens.set(newTokenCount);
            lastRefillTimestamp=now;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRefillRate() {
        return refillRate;
    }

    public AtomicInteger getTokens() {
        return tokens;
    }

    public long getLastRefillTimestamp() {
        return lastRefillTimestamp;
    }
}
