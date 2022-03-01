package iterator;

import java.util.List;

public class ListIterator<T> implements CustomIterator<T> {
    private final List<T> iterable;
    private Integer index;

    public ListIterator(List<T> iterable) {
        this.iterable = iterable;
        this.index = 0;
    }

    @Override
    public T next() {
        T element = iterable.get(index);
        index += 1;
        return element;
    }

    @Override
    public boolean hasNext() {
        return index < iterable.size();
    }
}
