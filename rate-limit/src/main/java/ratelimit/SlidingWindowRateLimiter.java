package ratelimit;

import ratelimit.model.Configuration;
import ratelimit.model.Customer;
import ratelimit.model.SlidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SlidingWindowRateLimiter implements RateLimiter {
    private static final Integer MAX_CREDITS = 100;
    private final Supplier<Long> timeSupplier;

    private final Map<Customer, SlidingWindow> customerWindowMap;
    private final Integer maxRequestsAllowed;
    private final Long maxTimeWindow;

    public SlidingWindowRateLimiter(Configuration configuration, Supplier<Long> timeSupplier) {
        this.customerWindowMap = new HashMap<>();
        this.maxRequestsAllowed = configuration.getMaxRequestsAllowed();
        this.maxTimeWindow = TimeUnit.MILLISECONDS.convert(configuration.getTimeInterval(), configuration.getTimeUnit());
        this.timeSupplier = timeSupplier;
    }

    @Override
    public boolean allow(Customer customer) {
        long thisRequestTime = timeSupplier.get();
        SlidingWindow customerWindow = customerWindowMap.getOrDefault(customer,
                new SlidingWindow(0, (maxRequestsAllowed), thisRequestTime));
        long expectedCount = (((thisRequestTime - customerWindow.getWindowStart()) / customerWindow.getWindowStart())
                * customerWindow.getPreviousCount()) + customerWindow.getCurrentRequestCount();

        if (thisRequestTime - customerWindow.getWindowStart() < maxTimeWindow) {
            if (expectedCount < maxRequestsAllowed) {
                customerWindow.incrementRequestCounter();
                customerWindowMap.put(customer, customerWindow);
                return true;
            } else if( expectedCount < customerWindow.getAvailableCredits()) {
                customerWindow.consumeCredit();
                customerWindowMap.put(customer, customerWindow);
                return true;
            }
        } else {
            customerWindow.resetWindow(0,
                    customerWindow.getCurrentRequestCount(),
                    customerWindow.getWindowStart() + maxTimeWindow,
                    Math.min(MAX_CREDITS , customerWindow.getAvailableCredits() + (maxRequestsAllowed - customerWindow.getCurrentRequestCount())));
            customerWindow.incrementRequestCounter();
            customerWindowMap.put(customer, customerWindow);
            return true;
        }
        return false;
    }
}
