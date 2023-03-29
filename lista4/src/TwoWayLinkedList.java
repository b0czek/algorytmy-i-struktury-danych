import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

public class TwoWayLinkedList<E> implements IList<E> {
    private Element head = null;
    private Element tail = null;
    private int size = 0;

    public boolean add(E value) {
        if (size == 0) {
            this.head = new Element(value);
            this.tail = this.head;
        } else {
            this.tail.setNext(new Element(value, null, this.tail));
            this.tail = this.tail.getNext();
        }
        this.size++;
        return true;
    }
    public boolean add(int index, E value) {
        if(index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if(size == index || size == 0) {
            this.add(value);
            return true;
        }
        Element e = this.getIndex(index);
        Element newElement = new Element(value, e, e.getPrevious());

        e.getPrevious().setNext(newElement);
        e.setPrevious(newElement);
        this.size++;
        return true;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean contains(E value) {
        return this.indexOf(value) != -1;
    }

    public E get(int index) {
        return this.getIndex(index).getValue();
    }

    public E set(int index, E value) {
        Element e = this.getIndex(index);
        E val = e.getValue();
        e.setValue(value);
        return val;
    }

    public int indexOf(E value) {
        int idx = 0;
        Element e = this.head;
        while(e != null) {
            if(Objects.equals(value, e.getValue())) {
                return idx;
            }
            idx++;
            e = e.getNext();
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
        if(index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Element element;
        try {
            element = this.getIndex(index);
        } catch(IndexOutOfBoundsException ex) {
            return null;
        }
        removeElement(element);
        return element.getValue();
    }


    public boolean remove(E value) {
        Element e = this.head;
        while(e != null) {
            if(Objects.equals(value, e.getValue())) {
                this.removeElement(e);
                return true;
            }
            e = e.getNext();
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void removeElement(Element element) {
        if(element == this.head) {
            this.head = element.getNext();
            this.head.setPrevious(null);
        }
        else if(element == this.tail) {
            this.tail = element.getPrevious();
            this.tail.setNext(null);
        }
        else {
            element.getPrevious().setNext(element.getNext());
            element.getNext().setPrevious(element.getPrevious());
        }
        size--;


    }

    private Element getIndex(int index) {
        if(index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Element e = this.head;
        while(index > 0) {
            index--;
            e = e.getNext();
        }
        return e;
    }

    private class Element {
        private E value;
        private Element next = null;
        private Element previous = null;

        public Element(E value) {
            this.value = value;
        }

        public Element(E value, Element next, Element previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element getPrevious() {
            return previous;
        }

        public void setPrevious(Element previous) {
            this.previous = previous;
        }

    }
    public Object[] asArray() {
        int size = this.size();
        Object[] array = new Object[size];
        Element el = this.head;
        for(int i = 0; i < size; i++, el = el.getNext()) {
            array[i] = el.getValue();
        }
        return array;
    }


}
