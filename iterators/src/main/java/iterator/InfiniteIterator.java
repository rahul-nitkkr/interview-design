package iterator;

import java.util.List;

public class InfiniteIterator<T> implements CustomIterator<T> {
    private final List<T> values;
    private Integer currIndex;

    public InfiniteIterator(List<T> values) {
        this.values = values;
        currIndex = 0;
    }

    @Override
    public T next() {
        T element = values.get(currIndex);
        currIndex = (currIndex + 1) % values.size();
        return element;
    }

    @Override
    public boolean hasNext() {
        return true;
    }
}
