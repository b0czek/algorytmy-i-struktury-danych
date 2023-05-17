package measuring;

import java.util.List;

public class CountingAdder<T> extends Counter {
    public void addLast(List<T> list, T value) {
        list.add(value);
        increment();
    }
    public void addLast(List<List<T>> list, List<T> value) {
        list.add(value);
        increment();
    }
}
