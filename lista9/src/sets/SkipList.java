package sets;

import java.util.*;

public class SkipList<T> implements ISet<T> {

    private Random random = new Random();

    private double p;
    private Comparator<T> comparator;
    private Node<T> head = new Node<>(null);

    public SkipList(Comparator<T> comparator, double p) {
        this.p = p;
        this.comparator = comparator;

    }

    @Override
    public void insert(T element) {

        Node<T> inserted = insert(element, head, head.references.length - 1);

        if(inserted != null && head.references.length < inserted.references.length) {
            int linksToAdd = inserted.references.length - head.references.length;
            head.growReferencesArray(inserted.references.length);
            for(int i = 0; i < linksToAdd; i++) {
                head.addReference(inserted);
            }
        }
    }

    private Node<T> insert(T element, Node<T> node, int n) {
        while(node != null) {
            Node<T> next = node.references[n];
            int comparison = next == null ? 1 : comparator.compare(next.value, element);
            if(comparison > 0) {
                Node<T> newNode = n == 0 ? new Node<>(element) : insert(element, node, n - 1);
                if(newNode != null && newNode.references.length > n) {
                    node.references[n] = newNode;
                    newNode.addReference(next);
                }
                return newNode;
            }
            else if(comparison < 0) {
                node = next;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
        Node<T> node = this.head;
        for(int i = node.references.length - 1; i >= 0; i--){
            if(node.references[i] == null) {
                continue;
            }
            int comparison = comparator.compare(node.references[i].value, element);
            if(comparison < 0) {
                node = node.references[i];
            }
            else if(comparison == 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean remove(T element) {
        return remove(element, head, head.references.length - 1) != null;
    }

    private Node<T> remove(T element, Node<T> node, int n) {
        for(int i = n; i >= 0; i--) {
            Node<T> next = node.references[i];
            if(next == null) {
                continue;
            }
            int comparison = comparator.compare(next.value, element);
            if(comparison == 0) {
                Node<T> removedNode = i == 0 ? next : remove(element, node, i - 1);
                if(removedNode != null) {
                    node.references[i] = next.references[i];

                }
                return removedNode;
            }
            else if(comparison < 0) {
                node = next;
                i = node.references.length;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "SkipList.p" + p;
    }

    private class Node<T> {
        T value;
        Node<T>[] references;

        Node(T value) {
            this.value = value;

            int referencesCount = drawReferencesCount();
            this.references = new Node[referencesCount];
        }

        int drawReferencesCount() {
            int c = 0;
            do {
                c++;
            } while(random.nextFloat() < p);

            return c;
        }

        void addReference(Node node) {

            for(int i = 0 ; i < references.length; i++) {
                if(references[i] == null) {
                    references[i] = node;
                    return;
                }
            }
            throw new IllegalStateException("node already has the references list full");
        }

        void growReferencesArray(int newSize) {
            if(newSize <= references.length) {
                throw new IllegalArgumentException("cant grow to smaller array");
            }
            Node[] newReferences = new Node[newSize];
            System.arraycopy(this.references, 0, newReferences, 0, this.references.length);
            this.references = newReferences;
        }


        int referencesCount() {
            for(int i = 0 ; i < references.length; i++) {
                if(references[i] == null) {
                    return i;
                }
            }
            return 0;
        }
    }

}
