package ratelimit;

import ratelimit.model.Configuration;
import ratelimit.model.Customer;
import ratelimit.model.FixedWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FixedWindowRateLimiter implements RateLimiter {

    private final Integer maxRequestsAllowed;
    private final Long maxTimeWindow;
    private final Map<Customer, FixedWindow> customerFixedWindowMap;

    public FixedWindowRateLimiter(Configuration configuration) {
        this.customerFixedWindowMap = new HashMap<>();
        this.maxRequestsAllowed = configuration.getMaxRequestsAllowed();
        this.maxTimeWindow = TimeUnit.MILLISECONDS.convert(configuration.getTimeInterval(), configuration.getTimeUnit());
    }

    @Override
    public boolean allow(Customer customer) {
        long currentRequestTime = System.currentTimeMillis();
        FixedWindow customerWindow = customerFixedWindowMap.getOrDefault(customer, new FixedWindow(currentRequestTime, maxRequestsAllowed));
        if (currentRequestTime - customerWindow.getWindowStart() < maxTimeWindow) {
            if (customerWindow.getAvailableRequests() > 0) {
                customerWindow.consumeRequest();
                customerFixedWindowMap.put(customer, customerWindow);
                return true;
            }
        } else {
            customerWindow.resetWindow(maxRequestsAllowed, customerWindow.getWindowStart() + maxTimeWindow);
            customerWindow.consumeRequest();
            customerFixedWindowMap.put(customer, customerWindow);
            return true;
        }

        return false;
    }
}
