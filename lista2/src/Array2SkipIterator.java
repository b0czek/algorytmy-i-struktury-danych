import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2SkipIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private int n;
    private T nextElem = null;

    public Array2SkipIterator(Array2<T> array, int n) {
        this.n = n;
        this.iterator = array.iterator();
        if(this.iterator.hasNext()) {
            this.nextElem = this.iterator.next();
        }
    }
    @Override
    public boolean hasNext() {
        return this.nextElem != null;
    }

    @Override
    public T next() {
        if(nextElem == null) {
            throw new NoSuchElementException();
        }

        T element = this.nextElem;
        for(int i = 0; i < this.n && this.nextElem != null; i++) {
            this.nextElem = this.iterator.hasNext() ? this.iterator.next() : null;
        }
        return element;
    }
}
