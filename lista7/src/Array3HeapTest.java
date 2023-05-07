import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

class Array3HeapTest {
    private Array3Heap<Integer> heap;
    @BeforeEach
    void setup() {
        heap = new Array3Heap<>(4, Integer::compareTo);
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


    }
}