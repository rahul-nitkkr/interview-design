package iterator;

public class InfiniteIteratorEven<T> implements CustomIterator<Long> {
    private Long curr;

    public InfiniteIteratorEven(Long start) {
        this.curr = start;
    }

    @Override
    public Long next() {
        Long val = curr;
        curr += 2;
        return val;
    }

    @Override
    public boolean hasNext() {
        return true;
    }
}
