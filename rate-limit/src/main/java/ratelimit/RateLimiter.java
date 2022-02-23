package ratelimit;

import ratelimit.model.Customer;

public interface RateLimiter {

    public boolean allow(Customer customer);
}
