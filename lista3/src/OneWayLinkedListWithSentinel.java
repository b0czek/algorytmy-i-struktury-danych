import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

public class OneWayLinkedListWithSentinel<E> implements IList<E> {
    private final Element head = new Element(null, null);

    @Override
    public boolean add(E value) {
        Element element = this.head;
        while(element.getNext() != null) {
            element = element.getNext();
        }
        element.setNext(new Element(value, null));
        return true;
    }

    @Override
    public boolean add(int index, E value) {
        Element prevElement = this.getElement(index - 1, true);

        Element element = new Element(value, prevElement.getNext());
        prevElement.setNext(element);

        return true;
    }

    @Override
    public void clear() {
        this.head.setNext(null);
    }

    @Override
    public boolean contains(E value) {
        return this.findPrevious(value) != null;
    }

    @Override
    public E get(int index) {
        Element element = this.getElement(index, false);
        return element.getValue();
    }

    @Override
    public E set(int index, E value) {
        Element element = this.getElement(index, false);
        E prevValue = element.getValue();
        element.setValue(value);

        return prevValue;
    }

    @Override
    public int indexOf(E value) {
        Element element = this.head;
        int index = 0;
        while((element = element.getNext()) != null && !Objects.equals(element.getValue(), value)) {
            index++;
        }
        return element == null ? -1 : index;
    }

    @Override
    public boolean isEmpty() {
        return this.head.getNext() == null;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        Element prevElement = this.getElement(index - 1, true);
        if(prevElement.getNext() == null) {
            throw new IndexOutOfBoundsException("index=" + index + "out of range, list size=" + this.size());

        }
        E value = prevElement.getNext().getValue();
        prevElement.setNext(prevElement.getNext().getNext());
        return value;
    }

    @Override
    public boolean remove(E value) {
        Element prevElement = this.findPrevious(value);
        if(prevElement == null) {
            return false;
        }
        prevElement.setNext(prevElement.getNext().getNext());
        return true;
    }

    @Override
    public int size() {
        Element element = this.head;
        int size = 0;
        while((element = element.getNext()) != null) {
            size++;
        }
        return size;
    }

    private Element getElement(int index, boolean allowGettingSentinel) {
        if(index < (allowGettingSentinel ? -1 : 0)) {
            throw new IndexOutOfBoundsException("index value was negative, index=" + index);
        }
        Element element = this.head;
        int idx = 0;
        while(idx <= index && (element = element.getNext()) != null) {
            idx++;
        }
        if(element == null) {
            throw new IndexOutOfBoundsException("index=" + index + "out of range, list size=" + this.size());
        }
        return element;

    }
    private Element findPrevious(E value) {
        Element previous = this.head;
        while(previous.getNext() != null && !Objects.equals(previous.getNext().getValue(), value)) {
            previous = previous.getNext();
        }
        if(previous.getNext() == null) {
            return null;
        }
        return previous;
    }

    private class Element {
        private E value;
        private Element next;

        public Element(E value, Element next) {
            this.value = value;
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public Element getNext() {
            return next;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    public Object[] asArray() {
        int size = this.size();
        Object[] array = new Object[size];
        Element el = this.head.getNext();
        for(int i = 0; i < size; i++, el = el.getNext()) {
            array[i] = el.getValue();
        }
        return array;
    }

}
