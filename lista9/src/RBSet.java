import java.util.Comparator;
import java.util.TreeSet;

public class RBSet<T> implements ISet<T> {
    private TreeSet<T> treeSet;

    public RBSet(Comparator<T> comparator) {
        treeSet = new TreeSet<>(comparator);

    }

    @Override
    public void insert(T element) {
        treeSet.add(element);
    }

    @Override
    public boolean contains(T element) {
        return treeSet.contains(element);
    }

    @Override
    public boolean remove(T element) {
        return treeSet.remove(element);
    }
}
