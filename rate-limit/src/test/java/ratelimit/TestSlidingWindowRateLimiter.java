package ratelimit;

import org.junit.jupiter.api.Test;
import ratelimit.model.Configuration;
import ratelimit.model.Customer;

import java.util.concurrent.TimeUnit;

public class TestSlidingWindowRateLimiter {

    @Test
    public void testSlidingWindow_Basic() {
        Configuration configuration = new Configuration(10, 1, TimeUnit.MILLISECONDS);
        RateLimiter rl = new SlidingWindowRateLimiter(configuration, new MockTimeSupplier(1L));
        Customer c1 = new Customer("cust-1");
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));

        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
        System.out.println(rl.allow(c1));
    }
}
