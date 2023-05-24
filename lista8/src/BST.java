import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class BST<T> {

    private Node root = null;
    private final Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T find(T value) {
        if(root == null) {
            return null;
        }
        Node result = root.search(value);
        return result == null ? null : result.value;
    }

    public T minimum() {
        if(root == null) {
            return null;
        }
        return root.min();
    }

    public T maximum() {
        if(root == null) {
            return null;
        }
        return root.max();
    }



    public <R> void preOrderWalk(Executor<T, R> executor) {
        if(root == null) {
            return;
        }
        root.preOrderWalk(executor);
    }

    public T successor(T value) {
        if(root == null) {
            throw new IllegalArgumentException("tree is empty");
        }
        Node node = root.search(value);
        if(node == null) {
            throw new IllegalArgumentException("value not present in the tree");
        }
        if(node.right != null) {
            return node.right.min();
        }
        Node r = this.root;
        Node successor = null;
        while(r != null) {
            int compareResult = comparator.compare(node.value, r.value);
            if(compareResult < 0) {
                successor = r;
                r = r.left;
            }
            else if(compareResult > 0) {
                r = r.right;
            } else {
                break;
            }
        }
        return successor == null ? null : successor.value;
    }

    public void insert(T value) {
        if(value == null) {
            throw new IllegalArgumentException("value must not be null");
        }

        Node node = new Node(value);
        if(this.root == null) {
            this.root = node;
            return;
        }
        Node prev = null;
        Node temp = root;
        while(temp != null) {
            int compareResult = comparator.compare(temp.value, value);
            if(compareResult > 0) {
                prev = temp;
                temp = temp.left;
            }
            else if (compareResult < 0) {
                prev = temp;
                temp = temp.right;
            }
            else {
                throw new IllegalArgumentException("value already present");
            }
        }
        int compareResult = comparator.compare(prev.value, value);
        if(compareResult > 0) {
            prev.left = node;
        }
        else if (compareResult < 0) {
            prev.right = node;
        }
    }

    public void delete(T value) {
        Node curr = root;
        Node prev = null;
        // find node
        while (curr != null && !Objects.equals(value, curr.value)) {
            prev = curr;
            if(comparator.compare(curr.value, value) > 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if(curr == null) {
            throw new NoSuchElementException();
        }

        if(curr.left == null || curr.right == null) {
            Node newCurrent = curr.left == null ? curr.right : curr.left;

            if(prev == null) {
                this.root = newCurrent;
                return;
            }

            if(curr == prev.left) {
                prev.left = newCurrent;
            } else {
                prev.right = newCurrent;
            }
        }
        else {
            Node p = null;
            Node temp = curr.right;

            while(temp.left != null) {
                p = temp;
                temp = temp.left;
            }

            if(p != null) {
                p.left = temp.right;
            } else {
                curr.right = temp.right;
            }
            curr.value = temp.value;
        }


    }

    private class Node {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
        }

        public Node search(T v) {
            if(Objects.equals(this.value, v)) {
                return this;
            }
            int compareResult = comparator.compare(this.value, v);
            if(compareResult > 0 && this.left != null) {
                return this.left.search(v);
            }
            else if(compareResult < 0 && this.right != null){
                return this.right.search(v);
            }
            return null;
        }
        public T min() {
            if(this.left != null) {
                return this.left.min();
            }
            return this.value;
        }
        public T max() {
            if(this.right != null) {
                return this.right.max();
            }
            return this.value;
        }
        public <R> void preOrderWalk(Executor<T, R> executor){

            executor.execute(value);
            if(left != null) {
                left.preOrderWalk(executor);
            }
            if(right != null) {

                right.preOrderWalk(executor);
            }

        }
    }

    public interface Executor<T, R> {
        void execute(T value);
        R getResult();
    }
}
