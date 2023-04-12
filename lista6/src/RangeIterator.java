import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeIterator<T> implements Iterator<T> {
    private int startIdx, endIdx;
    private Iterator<T> iterator;
    private int pos = 0;

    public RangeIterator(int startIdx, int endIdx, Iterator<T> iterator) {
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.iterator = iterator;

        while(this.pos != this.startIdx && iterator.hasNext()) {
            this.pos++;
            iterator.next();
        }


    }

    @Override
    public boolean hasNext() {
        return pos < endIdx && iterator.hasNext();
    }

    @Override
    public T next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        this.pos++;
        return iterator.next();
    }


    public int getPos() {
        return pos;
    }
}