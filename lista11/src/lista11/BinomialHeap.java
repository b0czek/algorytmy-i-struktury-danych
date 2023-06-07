
package lista11;

import java.util.Comparator;

public class BinomialHeap<T> {
    private BinomialTree<T> head = null;

    private Comparator<T> comparator;

    public BinomialHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinomialTree<T> maximum() {
        BinomialTree<T> max_node = this.head;
        BinomialTree<T> curr = this.head;
        T max = curr == null ? null : curr.getValue();
        while(curr != null) {
            if(comparator.compare(curr.getValue(), max) > 0) {
                max = curr.getValue();
                max_node = curr;
            }
            curr = curr.sibling;
        }

        return max_node;

    }

    public BinomialTree<T> insert(T value) {
        BinomialHeap<T> temp = new BinomialHeap<>(comparator);
        BinomialTree<T> tree = new BinomialTree<>(value);
        temp.head = tree;
        this.union(temp);
        return tree;
    }

    public void increaseValue(BinomialTree<T> node, T newValue) {
        if(comparator.compare(node.value, newValue) > 0) {
            throw new IllegalArgumentException("new value must be greater than current value");
        }
        node.value = newValue;

        var curr = node;
        var parent = node.parent;
        while (parent != null && comparator.compare(curr.value, parent.value) > 0 ) {
            T temp = curr.value;
            curr.value = parent.value;
            parent.value = temp;

            curr = parent;
            parent = curr.parent;
        }
    }

    public T extractMax() {
        if(head == null) {
            return null;
        }
        var max = maximum();
        var current = head;
        BinomialTree<T> prev = null;

        while(current != max) {
            prev = current;
            current = current.sibling;
        }
        if(prev == null) {
            head = current.sibling;
        }
        else {
            prev.sibling = current.sibling;
        }

        current = current.child;

        while(current != null) {
            current.parent = null;
            current = current.sibling;
        }
        if(max.child != null) {
            var reversedMaxChild = max.child.reverse();
            var tempHeap = new BinomialHeap<T>(comparator);
            tempHeap.head = reversedMaxChild;
            union(tempHeap);
        }

        return max.value;

    }


    public void union(BinomialHeap<T> heap2) {
        merge(heap2);

        if(this.head == null) {
            return;
        }
        BinomialTree<T> prev = null;
        BinomialTree<T> x = this.head;
        BinomialTree<T> next = this.head.sibling;

        while (next != null) {
            if((x.degree != next.degree)
                    || (next.sibling != null
                        && next.sibling.degree == x.degree)) {
                prev = x;
                x = next;
            } else {
                if (comparator.compare(x.getValue(), next.getValue()) >= 0) {
                    x.sibling = next.sibling;
                    next.linkTo(x);
                } else {
                    if (prev == null) {
                        this.head = next;
                    } else {
                        prev.sibling = next;
                    }
                    x.linkTo(next);
                    x = next;
                }
            }
            next = x.sibling;
        }

    }

    private void merge(BinomialHeap<T> mergedHeap) {
        BinomialTree<T> a = this.head;
        BinomialTree<T> b = mergedHeap.head;

        if(a == null) {
            this.head = b;
            return;
        }
        if(b == null) {
            return;
        }

        if(a.degree > b.degree) {
            a = b;
            b = this.head;
            this.head = a;
        }

        while(b != null) {
            if (a.sibling == null) {
                a.sibling = b;
                return;
            } else if (a.sibling.degree < b.degree) {
                a = a.sibling;
            } else {
                var c = b.sibling;
                b.sibling = a.sibling;
                a.sibling = b;
                a = a.sibling;
                b = c;
            }
        }


    }


    public void displayHeap()
    {
        System.out.print("\nHeap : ");
        displayHeap(head);
        System.out.println("\n");
    }

    private void displayHeap(BinomialTree<T> r)
    {
        if (r != null) {
            displayHeap(r.child);
            System.out.print(r.value + (r.parent == null ? "         " : " "));
            displayHeap(r.sibling);
        }
    }

}
