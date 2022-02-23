package ratelimit.model;

public class SlidingWindow {
    private Integer availableCredits;
    private Integer currentRequestCount;
    private Integer previousCount;
    private Long windowStart;

    public SlidingWindow(Integer currentRequestCount, Integer previousCount, Long windowStart) {
        this.currentRequestCount = currentRequestCount;
        this.previousCount = previousCount;
        this.windowStart = windowStart;
        this.availableCredits = 0;
    }

    public Integer getCurrentRequestCount() {
        return currentRequestCount;
    }

    public Integer getPreviousCount() {
        return previousCount;
    }

    public Long getWindowStart() {
        return windowStart;
    }

    public Integer getAvailableCredits() {
        return availableCredits;
    }

    public void incrementRequestCounter() {
        this.currentRequestCount += 1;
    }

    public void consumeCredit() {
        this.availableCredits -= 1;
    }

    public void resetWindow(Integer currentRequestCount, Integer previousCount, Long windowStart, Integer credits) {
        this.windowStart = windowStart;
        this.currentRequestCount = currentRequestCount;
        this.previousCount = previousCount;
        this.availableCredits = credits;
    }
}
