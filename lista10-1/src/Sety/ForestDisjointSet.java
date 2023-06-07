package Sety;

public class ForestDisjointSet implements DisjointSet<ForestDisjointSet>{
    ForestDisjointSet parent;
    int rank;


    public ForestDisjointSet() {
        this.rank = 0;
        this.parent = this;

    }

    @Override
    public boolean union(ForestDisjointSet y) {
        ForestDisjointSet rootx = findSet();
        ForestDisjointSet rooty = y.findSet();
        if(rootx == rooty) {
            return false;
        }
        if(rootx.rank > rooty.rank) {
            rooty.parent = rootx.parent;
        }
        else if (rootx.rank < rooty.rank) {
            rootx.parent = rooty.parent;
        }
        else {
            rootx.parent = rooty.parent;
            rooty.rank++;
        }

        return true;
    }

    @Override
    public ForestDisjointSet findSet() {
        if(this.parent != this) {
            this.parent = parent.findSet();

        }
        return this.parent;
    }
}
