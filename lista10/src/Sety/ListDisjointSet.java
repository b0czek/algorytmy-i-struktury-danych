package Sety;

public class ListDisjointSet implements DisjointSet<Node>{


    @Override
    public Node makeSet() {
        Node node = new Node();
        return node;

    }

    @Override
    public boolean union(Node x, Node y) {
        Node rootx = findSet(x);
        Node rooty = findSet(y);
        if(rootx == rooty) {
            return false;
        }
        if(rootx.getLength() > rooty.getLength()) {
            join(rootx, rooty);
        } else {
            join(rooty, rootx);
        }

        return true;
    }

    private void join(Node rootx, Node rooty) {
        rootx.getLast().setNext(rooty);
        rootx.setLast(rooty.getLast());
        rootx.setLength(rootx.getLength() + rooty.getLength());

        for(Node node = rooty; node != null; node = node.getNext()) {
            node.setRepresentant(rootx);
        }


    }

    @Override
    public Node findSet(Node x) {
        return x.getRepresentant();
    }
}
