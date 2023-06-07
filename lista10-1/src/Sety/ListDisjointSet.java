package Sety;

public class ListDisjointSet implements DisjointSet<ListDisjointSet> {
    private ListDisjointSet representant;
    private ListDisjointSet next;
    private ListDisjointSet last;
    private int length;

    public ListDisjointSet() {
        this.representant = this;
        this.next = null;
        this.last = this;
        this.length = 1;
    }


    @Override
    public boolean union(ListDisjointSet y) {
        ListDisjointSet rootx = findSet();
        ListDisjointSet rooty = y.findSet();
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

    private void join(ListDisjointSet rootx, ListDisjointSet rooty) {
        rootx.getLast().setNext(rooty);
        rootx.setLast(rooty.getLast());
        rootx.setLength(rootx.getLength() + rooty.getLength());

        for(ListDisjointSet node = rooty; node != null; node = node.getNext()) {
            node.setRepresentant(rootx);
        }


    }


    public ListDisjointSet findSet() {
        return this.getRepresentant();
    }




    public int getLength() {
        return representant.length;
    }

    public void setLength(int length) {
        representant.length = length;
    }


    public ListDisjointSet getLast() {
        return representant.last;
    }

    public void setLast(ListDisjointSet last) {
        this.representant.last = last;
    }

    public ListDisjointSet getRepresentant() {
        return representant;
    }

    public void setRepresentant(ListDisjointSet representant) {
        this.representant = representant;
    }

    public ListDisjointSet getNext() {
        return next;
    }

    public void setNext(ListDisjointSet next) {
        this.next = next;
    }




}