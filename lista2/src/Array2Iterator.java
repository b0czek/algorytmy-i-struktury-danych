import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2Iterator <T> implements Iterator<T> {
    private Array2<T> array;
    private int i,j = 0;

    public Array2Iterator(Array2<T> array) {
        this.array = array;
        if(!this.array.fieldExists(0,0)) {
            this.moveToNextElement();
        }
    }

    private void moveToNextElement() {
        int[] indexes = this.nextElementIndexes();
        if(indexes != null) {
            i = indexes[0];
            j = indexes[1];
        }
        else {
            j++;
        }
    }
    private int[] nextElementIndexes() {
        if(j+1 < this.array.elemLength(i)) {
            return new int[] { i, j+1 };
        }
        for(int k = i + 1; k < this.array.length(); k++){
            if(this.array.elemLength(k) != 0){
                return new int[] { k, 0 };
            }
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return this.array.fieldExists(i,j);
    }

    @Override
    public T next() {
        T item = this.array.get(i,j);
        this.moveToNextElement();

        return item;
    }
}
