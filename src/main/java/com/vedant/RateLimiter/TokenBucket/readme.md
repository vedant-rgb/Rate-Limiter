## Token Bucket Rate Limiter
### Types:
- Scheduled Refill
- On-Demand Refill


### Components:
- Capacity: Maximum number of tokens in the bucket.
- Refill Rate: Rate at which tokens are added to the bucket.
- Tokens: Units that allow requests to be processed.
- Last Refill Timestamp: Time when the bucket was last refilled.

### Operations:
- Allow Request: Check if a request can be processed based on available tokens.
- Refill Tokens: Add tokens to the bucket based on the refill rate and elapsed time.
- Consume Tokens: Deduct tokens from the bucket when a request is processed.

### Scheduled Refill:
- Tokens are added to the bucket at fixed intervals.
- Example: Add 5 tokens every second.
- Implementation: Use a scheduled task to refill tokens.
- Pros: Simple to implement, predictable behavior.
- Cons: May lead to burstiness if many requests arrive just after a refill.

### On-Demand Refill:
- Tokens are added to the bucket only when a request arrives.
- Example: Calculate tokens to add based on elapsed time since last request.
- Implementation: Check and refill tokens during each request i.e elapsedTime(now-last request time)*refillRate. 
- Pros: More responsive to traffic patterns, smoother rate limiting.
- Cons: Slightly more complex to implement.
