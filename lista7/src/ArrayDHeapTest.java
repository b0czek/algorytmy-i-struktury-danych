import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

class ArrayDHeapTest {
    private ArrayDHeap<Integer> heap;
    @BeforeEach
    void setup() {
        heap = new ArrayDHeap<>(4, 10, Integer::compareTo);
    }

    @org.junit.jupiter.api.Test
    void clear() {
        heap.clear();
        assertThrowsExactly(NoSuchElementException.class, () -> heap.minimum());

        heap.add(1);
        heap.add(1);
        heap.add(1);
        heap.clear();
        assertThrowsExactly(NoSuchElementException.class, () -> heap.minimum());


    }

    @org.junit.jupiter.api.Test
    void add() {
        assertThrowsExactly(IllegalArgumentException.class, ()->heap.add(null));

        heap.add(1);
        assertEquals(heap.minimum(), 1);

        heap.clear();
        heap.add(1);
        heap.add(1);
        assertEquals(heap.minimum(), 1);

        heap.clear();
        heap.add(1);
        heap.add(1);
        assertEquals(heap.minimum(), 1);

        heap.clear();
        for(int i = 10; i > 0 ; i --) {
            heap.add(i);
        }
        for(int i = 1; i <= 10; i++) {
            assertEquals(heap.minimum(), i);
        }

        heap.clear();
        Random random = new Random();
        int[] arr = new int[1000];
        for(int i = 0 ; i < 1000; i++) {
            arr[i] = random.nextInt(1000);
            heap.add(arr[i]);
        }
        arr = Arrays.stream(arr).sorted().toArray();
        for(int i = 0 ; i < 1000; i++) {
            int min = heap.minimum();
            assertEquals(min, arr[i]);
        }

    }

    @org.junit.jupiter.api.Test
    void minimum() {
        assertThrowsExactly(NoSuchElementException.class, () -> heap.minimum());

        heap.add(1);
        assertEquals(heap.minimum(), 1);

        heap.add(5);
        heap.add(10);
        heap.add(4);
        heap.add(2);
        heap.add(1);
        heap.add(14);

        assertEquals(heap.minimum(), 1);
        assertEquals(heap.minimum(), 2);
        assertEquals(heap.minimum(), 4);
        assertEquals(heap.minimum(), 5);
        assertEquals(heap.minimum(), 10);
        assertEquals(heap.minimum(), 14);



    }
}