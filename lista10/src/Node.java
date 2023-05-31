public class Node {
    private Node representant;
    private Node next;
    private Node last;
    private int length;


    public Node() {
        this.representant = this;
        this.next = null;
        this.last = this;
        this.length = 1;
    }

    public int getLength() {
        return representant.length;
    }

    public void setLength(int length) {
        representant.length = length;
    }


    public Node getLast() {
        return representant.last;
    }

    public void setLast(Node last) {
        this.representant.last = last;
    }

    public Node getRepresentant() {
        return representant;
    }

    public void setRepresentant(Node representant) {
        this.representant = representant;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}