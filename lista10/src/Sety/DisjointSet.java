package Sety;

public interface DisjointSet<Node> {
    Node makeSet();
    boolean union(Node x, Node y);
    Node findSet(Node x);


}
