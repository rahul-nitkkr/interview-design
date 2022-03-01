package iterator;

import java.util.ArrayList;
import java.util.List;

public class ConcatIterator<T> implements CustomIterator<T> {
    private final List<CustomIterator<T>> childIterators;
    private int listIndex;

    public ConcatIterator(CustomIterator<CustomIterator<T>> iterators) {
        this.childIterators = new ArrayList<>();
        while (iterators.hasNext()) {
            childIterators.add(iterators.next());
        }
        listIndex = 0;
    }

    @Override
    public T next() {
        if (!childIterators.get(listIndex).hasNext())
            listIndex += 1;
        return childIterators.get(listIndex).next();
    }

    @Override
    public boolean hasNext() {
        return childIterators.stream().filter(CustomIterator::hasNext).count() > 0;
    }
}
