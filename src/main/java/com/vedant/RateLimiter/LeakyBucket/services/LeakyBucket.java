package com.vedant.RateLimiter.LeakyBucket.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LeakyBucket {

    private final int bucket_capacity;
    private final int leak_rate;
    private final AtomicInteger current_water_level = new AtomicInteger(0);
    private final Queue<String> bucket = new ConcurrentLinkedQueue<>();

    public LeakyBucket(@Value("${leakyBucket.bucketCapacity}")int bucket_capacity,
                       @Value("${leakyBucket.leakRate}") int leak_rate) {
        this.bucket_capacity = bucket_capacity;
        this.leak_rate = leak_rate;
    }

    public AtomicInteger getCurrent_water_level() {
        return current_water_level;
    }

    public synchronized boolean allowed(){
        if(current_water_level.get() < bucket_capacity){
            bucket.add("request");
            current_water_level.incrementAndGet();
            return true;
        }
        return false;
    }

    @Scheduled(fixedDelay = 1000)
    public void leak(){
        int leaks = Math.min(leak_rate,current_water_level.get());
        for(int i=0;i<leaks;i++){
            String removed = bucket.poll();
            if(removed!=null){
                current_water_level.decrementAndGet();
            }
        }



    }
}
