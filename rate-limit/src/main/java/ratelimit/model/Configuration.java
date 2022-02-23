package ratelimit.model;

import java.util.concurrent.TimeUnit;

public class Configuration {
    private final Integer maxRequestsAllowed;
    private final Integer timeInterval;
    private final TimeUnit timeUnit;

    public Configuration(Integer maxRequestsAllowed, Integer timeInterval, TimeUnit timeUnit) {
        this.maxRequestsAllowed = maxRequestsAllowed;
        this.timeInterval = timeInterval;
        this.timeUnit = timeUnit;
    }

    public Integer getMaxRequestsAllowed() {
        return maxRequestsAllowed;
    }

    public Integer getTimeInterval() {
        return timeInterval;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
