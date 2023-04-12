package measuring;

import java.util.List;

public class CountingAdder<T> extends Counter {
    public void addLast(List<T> list, T value) {
        list.add(value);
        increment();
    }
}
