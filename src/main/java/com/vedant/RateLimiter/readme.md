# Rate Limiter
A rate limiter is a mechanism used to control the rate of requests or actions in a system. It helps prevent abuse, ensures fair usage, and maintains system stability by limiting the number of requests that can be processed within a specified time frame.
## Types of Rate Limiting Algorithms
1. **Fixed Window**: Limits the number of requests in a fixed time window (e.g., 100 requests per minute). Simple but can lead to burstiness at the edges of the window.
2. **Sliding Window**: Similar to fixed window but uses a rolling time window, providing a smoother rate limiting experience.
3. **Leaky Bucket**: Requests are added to a queue and processed at a constant rate. Excess requests are dropped if the queue is full.
4. **Token Bucket**: Tokens are added to a bucket at a fixed rate. Each request consumes a token. If no tokens are available, the request is denied. Allows for bursts of traffic.

## Source: https://bytebytego.com/courses/system-design-interview/design-a-rate-limiter