package iterator;

import java.util.List;
import java.util.stream.Collectors;

public class RoundRobinIterator<T> implements CustomIterator<T> {
    private final List<CustomIterator<T>> parentIterator;
    private Integer listIndex;

    public RoundRobinIterator(List<List<T>> listOfLists) {
        parentIterator = listOfLists.stream().map(ListIterator::new).collect(Collectors.toList());
        listIndex = 0;
    }

    @Override
    public T next() {
        T element = parentIterator.get(listIndex).next();
        listIndex = (listIndex + 1) % parentIterator.size();
        return element;
    }

    @Override
    public boolean hasNext() {
        return parentIterator.stream().filter(CustomIterator::hasNext).count() > 0;
    }
}
