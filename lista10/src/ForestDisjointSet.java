public class ForestDisjointSet implements DisjointSet<ForestNode> {
    @Override
    public ForestNode makeSet() {
        return new ForestNode();
    }

    @Override
    public boolean union(ForestNode x, ForestNode y) {
        ForestNode rootx = findSet(x);
        ForestNode rooty = findSet(y);
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
    public ForestNode findSet(ForestNode x) {
        if(x.parent != x) {
            x.parent = findSet(x.parent);

        }
        return x.parent;
    }
}
