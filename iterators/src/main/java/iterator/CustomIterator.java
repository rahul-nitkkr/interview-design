package iterator;

/**
 * Simple Iterator from list
 * Iterator from another Iterator
 * Merge Iterator from list of iterators in Round Robin fashion
 * Merge Iterator from iterator of iterators in Round Robin fashion
 * Merge Iterator from iterator of iterators one after another
 * Step Iterator using start / end / step => Handle scenarios
 * Given a list of values, create a infinite cyclic iterator
 * @param <T>
 */
public interface CustomIterator<T> {

    T next();

    boolean hasNext();
}
