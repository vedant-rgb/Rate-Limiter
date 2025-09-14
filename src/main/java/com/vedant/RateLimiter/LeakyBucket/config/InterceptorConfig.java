package com.vedant.RateLimiter.LeakyBucket.config;

import com.vedant.RateLimiter.LeakyBucket.interceptor.LeakyBucketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final LeakyBucketInterceptor interceptor;

    public InterceptorConfig(LeakyBucketInterceptor interceptor) {
        this.interceptor = interceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/leakyBucket/**");
    }
}
