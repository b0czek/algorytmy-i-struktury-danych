import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayDHeap<T> {
    private T[] array;
    private int n = 0;
    private int d;
    private Comparator<T> comparator;
    public ArrayDHeap(int baseCapacity, int d, Comparator<T> comparator) {
        array = (T[]) new Object[baseCapacity];
        if(d < 1) {
            throw new IllegalArgumentException("invalid d value");
        }
        this.d = d;
        this.comparator = comparator;
    }

    public void clear() {
        array = (T[]) new Object[array.length];
        n = 0;
    }

    public void add(T element) {
        if(element == null) {
            throw new IllegalArgumentException("element cannot be null");
        }
        if(n == array.length) {
            this.array = growArray();
        }
        array[n++] = element;
        swim(n - 1);
    }

    public T minimum() {
        if( n == 0 ) {
            throw new NoSuchElementException("heap is empty");
        }

        T val = array[0];
        if(n > 1) {
            array[0] = array[n - 1];
            sink(0);
        }
        n--;
        array[n] = null;
        return val;
    }



    private void swim(int idx) {
        int parent;
        while(idx != 0 && comparator.compare(array[idx], array[(parent = (idx - 1) / d)]) < 0) {
            swap(idx, parent);
            idx = parent;
        }
    }

    private void sink(int idx) {
        int child;
        while((child=d*idx + 1) < n) {
            int minIdx = child;
            for(int i = 1; i < d; i++) {
                if(child < n - i && comparator.compare(array[minIdx], array[child + i]) > 0) {
                    minIdx = child + i;
                }
            }
            child = minIdx;

            if(comparator.compare(array[idx], array[child]) > 0) {
                swap(idx, child);
                idx = child;
            } else {
                break;
            }
        }
    }

    private T[] growArray() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return (T[])newArray;
    }

    private void swap(int idx1, int idx2) {
        T temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }
}
