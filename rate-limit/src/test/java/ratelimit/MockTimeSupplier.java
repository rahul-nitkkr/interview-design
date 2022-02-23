package ratelimit;

import java.util.function.Supplier;

public class MockTimeSupplier implements Supplier<Long> {
    private Long curr;

    public MockTimeSupplier(Long curr) {
        this.curr = curr;
    }

    @Override
    public Long get() {

        curr += 1L;
        return curr;
    }
}
