package iterator;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TestListIterator {

    @Test
    public void testListIterator() {
        List<String> iterable = List.of("A", "B", "C");
        CustomIterator<String> iterator = new ListIterator<String>(iterable);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testIteratorIterator() {
        List<String> iterable = List.of("A", "B", "C");
        CustomIterator<String> iterator = new ListIterator<String>(iterable);

        CustomIterator<String> iterator2 = new IteratorIterator<String>(iterator);
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

    @Test
    public void testRoundRobinIterator() {
        List<String> iterable1 = List.of("A", "B", "C");
        List<String> iterable2 = List.of("D", "E", "F");
        List<String> iterable3 = List.of("G", "H", "I");
        List<String> iterable4 = List.of("J", "K", "L");
        CustomIterator<String> iterator = new RoundRobinIterator<>(List.of(iterable1, iterable2, iterable3, iterable4));

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testConcatIterator() {
        CustomIterator<String> iterable1 = new ListIterator<>(List.of("A", "B", "C"));
        CustomIterator<String> iterable2 = new ListIterator<>(List.of("D", "E", "F"));
        CustomIterator<String> iterable3 = new ListIterator<>(List.of("G", "H", "I"));
        CustomIterator<String> iterable4 = new ListIterator<>(List.of("J", "K", "L"));

        CustomIterator<String> iterator = new ConcatIterator<>(new ListIterator<>(List.of(iterable1, iterable2, iterable3, iterable4)));

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testStepIterator() {
        List<String> iterable = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");
        CustomIterator<String> iterator = new StepIterator<>(iterable, 0, iterable.size(), 3, false);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        CustomIterator<String> iterator2 = new StepIterator<>(iterable, iterable.size() - 1, 0, -2, false);

        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

    @Test
    public void testInfiniteIterator() {
        List<String> iterable = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L");
        CustomIterator<String> iterator = new InfiniteIterator<>(iterable);
        int i = 0;

        while (iterator.hasNext() && i < 100) {
            System.out.println(iterator.next());
            i += 1;
        }
    }

    @Test
    public void testInfiniteIterator_Even() {
        CustomIterator<Long> iterator = new InfiniteIteratorEven<Long>(2L);
        int i = 0;

        while (iterator.hasNext() && i < 100) {
            System.out.println(iterator.next());
            i += 1;
        }
    }
}
