import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private BST<Integer> bst;
    @BeforeEach
    void setup() {
        bst = new BST<>(Integer::compareTo);
    }

    List<Integer> addShuffled(int count) {
        List<Integer> added = IntStream.range(0, count).boxed().collect(Collectors.toList());
        Collections.shuffle(added);
        for(int i : added) {
            bst.insert(i);
        }
        return added;
    }

    void add() {
        int[] insertions = new int[] {
                7,5,10,3,8,12
        };
        for(int i : insertions) {
            bst.insert(i);
        }

    }

    @org.junit.jupiter.api.Test
    void find() {
        assertNull(bst.find(1));

        addShuffled(10);
        for(int i = 0 ; i < 10; i++) {
            assertEquals(i, bst.find(i));
        }
        assertNull(bst.find(10));


    }

    @org.junit.jupiter.api.Test
    void minimum() {
        assertNull(bst.minimum());

        addShuffled(10);
        assertEquals(0, bst.minimum());

    }

    @org.junit.jupiter.api.Test
    void maximum() {
        assertNull(bst.maximum());

        addShuffled(10);
        assertEquals(9, bst.maximum());
    }

    @org.junit.jupiter.api.Test
    void preOrderWalk() {
        add();
        walkCheck(new int[] {
                7,5,3,10,8,12
        });
    }

    @org.junit.jupiter.api.Test
    void successor() {
        add();
        assertEquals(5, bst.successor(3));
        assertEquals(7, bst.successor(5));
        assertEquals(8, bst.successor(7));
        assertEquals(10, bst.successor(8));
        assertEquals(12, bst.successor(10));
        assertNull(bst.successor(12));

        assertNull(bst.successor(20));
    }

    @org.junit.jupiter.api.Test
    void insert() {
        assertThrowsExactly(IllegalArgumentException.class, () -> bst.insert(null));


    }

    @org.junit.jupiter.api.Test
    void delete() {
        assertThrowsExactly(NoSuchElementException.class, () -> bst.delete(1));
        add();
        bst.insert(4);

        bst.delete(4);
        walkCheck(new int[]{7, 5, 3, 10, 8, 12});

        bst.delete(3);
        walkCheck(new int[]{7, 5, 10, 8, 12});

        bst.delete(10);
        walkCheck(new int[]{7, 5, 12, 8});

        bst.delete(7);
        walkCheck(new int[]{8, 5, 12});

        bst.delete(5);
        walkCheck(new int[]{8,12});

        bst.delete(8);
        walkCheck(new int[]{12});

    }

    private void walkCheck(int[] expectedOrder) {
        Executor executor = new Executor();
        bst.preOrderWalk(executor);
        ArrayList<Integer> result = executor.getResult();

        for(int i = 0 ; i < expectedOrder.length; i++) {
            assertEquals(result.get(i), expectedOrder[i]);
        }
    }


    private class Executor implements BST.Executor<Integer, ArrayList<Integer>> {
        private ArrayList<Integer> list = new ArrayList<>();
        @Override
        public void execute(Integer value) {
            list.add(value);
        }

        @Override
        public ArrayList<Integer> getResult() {
            return list;
        }
    }
}