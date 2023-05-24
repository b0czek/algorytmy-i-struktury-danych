package sets;

import java.util.Comparator;
import java.util.Objects;

public class BSTSet<T> implements ISet<T> {

    private Node root = null;
    private final Comparator<T> comparator;

    public BSTSet(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean contains(T value) {
        return find(value) != null;
    }

    public T find(T value) {
        Node result = search(root, value);
        return result == null ? null : result.value;
    }

    private Node search(Node node, T value) {
        if(node == null || Objects.equals(node.value, value)) {
            return node;
        }
        if(comparator.compare(node.value, value) > 0) {
            return search(node.left, value);
        }
        else {
            return search(node.right, value);
        }
    }

    public T minimum() {
        if(root == null) {
            return null;
        }
        return min(root);
    }

    private T min(Node node) {
        if(node.left != null) {
            return min(node.left);
        }
        return node.value;
    }

    public T maximum() {
        if(root == null) {
            return null;
        }
        return max(root);
    }

    private T max(Node node) {
        if(node.right != null) {
            return max(node.right);
        }
        return node.value;
    }

    public <R> void preOrderWalk(Executor<T, R> executor) {
        if(root == null) {
            return;
        }
        preOrderWalk(root, executor);
    }
    private <R> void preOrderWalk(Node node, Executor<T, R> executor){
        if(node != null) {
            executor.execute(node.value);
            preOrderWalk(node.left, executor);
            preOrderWalk(node.right, executor);
        }
    }

    public T successor(T value) {
        Node node = search(root, value);
        if(node == null) {
            return null;
        }
        if(node.right != null) {
            return min(node.right);
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
        }
        int compareResult = comparator.compare(prev.value, value);
        if(compareResult > 0) {
            prev.left = node;
        }
        else if (compareResult < 0) {
            prev.right = node;
        }
    }

    public boolean remove(T value) {
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
            return false;
        }

        if(curr.left == null || curr.right == null) {
            Node newCurrent = curr.left == null ? curr.right : curr.left;

            if(prev == null) {
                this.root = newCurrent;
                return true;
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
        return true;

    }

    private class Node {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
        }
    }

    public interface Executor<T, R> {
        void execute(T value);
        R getResult();
    }

    @Override
    public String getName() {
        return "BSTSet";
    }
}
