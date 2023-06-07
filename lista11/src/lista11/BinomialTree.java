package lista11;


public class BinomialTree<T> {
    public T value;
    public BinomialTree<T> parent = null;
    public BinomialTree<T> child = null;

    public BinomialTree<T> sibling = null;

    public int degree = 0;

    public BinomialTree(T value) {
        this.value = value;
    }

    public void linkTo(BinomialTree<T> z) {
        parent = z;
        sibling = z.child;
        z.child = this;
        z.degree += 1;
    }

    public BinomialTree<T> reverse() {
        return reverse(null);
    }

    private BinomialTree<T> reverse(BinomialTree<T> sib) {
        BinomialTree<T> last = sibling == null ? this : sibling.reverse(this);
        sibling = sib;
        return last;

    }

    public T getValue() {
        return value;
    }

}
