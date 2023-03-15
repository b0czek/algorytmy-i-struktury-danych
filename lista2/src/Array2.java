import java.util.Iterator;

public class Array2<T> implements Iterable<T> {
    private T[][] array;

    private T[][] initArray(int[] elements) {
        T[][] array = (T[][]) new Object[elements.length][];
        for(int i = 0; i < elements.length; i++) {
            array[i] = (T[]) new Object[elements[i]];

            for(int j = 0; j < elements[i]; j++) {
                array[i][j] = null;
            }
        }
        return array;
    }

    public boolean fieldExists(int i, int j) {
        return i < this.array.length && j < this.array[i].length;
    }

    public Array2(int[] elements) {
        if(elements == null) {
            throw new IllegalArgumentException();
        }
        this.array = initArray(elements);
    }

    public T get(int i, int j) {
        if(!fieldExists(i,j)) {
            throw new IllegalArgumentException("field does not exist");
        }
        return this.array[i][j];
    }

    public void set(T newElem, int i, int j) {
        if(!fieldExists(i,j)) {
            throw new IllegalArgumentException("field does not exist");
        }
        this.array[i][j] = newElem;
    }

    public int length() {
        return this.array.length;
    }
    public int elemLength(int i) {
        if(i >= this.array.length) {
            throw new IllegalArgumentException("field does not exist");
        }
        return this.array[i].length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Array2Iterator<>(this);
    }

    public Iterator<T> iterator(int n) {
        return new Array2SkipIterator<>(this, n);
    }
}
