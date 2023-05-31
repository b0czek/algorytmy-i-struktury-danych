import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListDisjointSetTest {
    ListDisjointSet set;
    @BeforeEach
    void setup() {
        set = new ListDisjointSet();
    }
    @Test
    void makeSet() {
        Node node = set.makeSet();
        assertNotNull(node);
        assertEquals(node, node.getRepresentant());
        assertEquals(1, node.getLength());
        assertEquals(node, node.getLast());
        assertEquals(null, node.getNext());
    }

    @Test
    void union() {
        Node node1 = set.makeSet();
        Node node2 = set.makeSet();
        assertFalse(set.union(node1, node1));

        set.union(node1, node2);
        assertFalse(set.union(node1, node2));

        assertEquals(2, node1.getLength());
        assertEquals(node1, node2.getLast());
        assertEquals(node1, node2.getNext());
        assertEquals(node2, node1.getRepresentant());
    }

    @Test
    void findSet() {
        Node node1 = set.makeSet();

        Node node2 = set.makeSet();
        set.union(node1,node2);
        assertEquals(node2, set.findSet(node1));
    }
}