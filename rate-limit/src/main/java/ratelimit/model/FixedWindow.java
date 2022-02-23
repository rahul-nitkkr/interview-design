package ratelimit.model;

public class FixedWindow {
    private long windowStart;
    private long availableRequests;

    public FixedWindow(long windowStart, long availableRequests) {
        this.windowStart = windowStart;
        this.availableRequests = availableRequests;
    }

    public long getWindowStart() {
        return windowStart;
    }

    public long getAvailableRequests() {
        return availableRequests;
    }

    public void consumeRequest(){
        this.availableRequests -= 1;
    }

    public void resetWindow(final long allowedRequests, final long windowStart) {
        this.availableRequests = allowedRequests;
        this.windowStart = windowStart;
    }
}
