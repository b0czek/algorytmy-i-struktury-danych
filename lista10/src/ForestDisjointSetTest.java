import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ForestDisjointSetTest {
    ForestDisjointSet set;
    @BeforeEach
    void setup() {
        set = new ForestDisjointSet();
    }
    @org.junit.jupiter.api.Test
    void makeSet() {
        ForestNode node = set.makeSet();
        assertNotNull(node);
        assertEquals(node, node.parent);
        assertEquals(0, node.rank);
    }

    @org.junit.jupiter.api.Test
    void union() {
        ForestNode node1 = set.makeSet();
        ForestNode node2 = set.makeSet();
        ForestNode node3 = set.makeSet();

        assertFalse(set.union(node1, node1));

        set.union(node1, node2);
        assertEquals(node1.parent, node2);
        assertEquals(node2.rank, 1);

        assertFalse(set.union(node2, node1));


        set.union(node2, node3);
        assertEquals(node3.parent, node2.parent);

    }

    @org.junit.jupiter.api.Test
    void findSet() {
        ForestNode node1 = set.makeSet();
        ForestNode node2 = set.makeSet();

        set.union(node1, node2);

        assertEquals(set.findSet(node1), node2);


    }
}