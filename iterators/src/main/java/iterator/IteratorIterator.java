package iterator;

public class IteratorIterator<T> implements CustomIterator<T> {
    private final CustomIterator<T> childIterator;

    public IteratorIterator(CustomIterator<T> childIterator) {
        this.childIterator = childIterator;
    }

    @Override
    public T next() {
        return childIterator.next();
    }

    @Override
    public boolean hasNext() {
        return childIterator.hasNext();
    }
}
