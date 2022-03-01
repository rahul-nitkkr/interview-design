package iterator;

import java.util.List;


public class StepIterator<T> implements CustomIterator<T> {

    private final List<T> inputList;
    private final Integer start, end, step;
    private final boolean endInclusive;
    private Integer currIndex;

    public StepIterator(List<T> inputList, Integer start, Integer end, Integer step, boolean endInclusive) {
        this.inputList = inputList;
        this.start = start;
        this.end = end;
        this.step = step;
        this.endInclusive = endInclusive;
        this.currIndex = start;
    }

    @Override
    public T next() {
        T element = inputList.get(currIndex);
        currIndex += step;
        return element;
    }

    @Override
    public boolean hasNext() {
        if (step > 0)
            return endInclusive ? currIndex <= end - 1 : currIndex < end - 1;
        else
            return endInclusive ? currIndex >= end : currIndex > end;
    }
}
