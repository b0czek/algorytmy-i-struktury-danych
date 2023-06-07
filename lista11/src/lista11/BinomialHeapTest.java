package lista11;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class BinomialHeapTest {
    private BinomialHeap<Integer> heap;
    private int[] heapToCreate = new int[] {7,8,14,27,11,10,30,25,  3, 13, 20,19,   9};
    @BeforeEach
    void setup() {
        heap = new BinomialHeap<>(Integer::compareTo);
    }

    @org.junit.jupiter.api.Test
    void maximum() {
        int max = heapToCreate[0];
        for(int num : heapToCreate) {
            heap.insert(num);
            if(num > max) {
                max = num;
            }
            assertEquals(max, heap.maximum().value);
        }
    }

    @org.junit.jupiter.api.Test
    void insert() {
        Arrays.stream(heapToCreate).forEach(num -> heap.insert(num));
        assertEquals(Arrays.stream(heapToCreate).max().getAsInt(), heap.maximum().value);
        heap.displayHeap();
    }

    @org.junit.jupiter.api.Test
    void increaseValue() {
        var first = heap.insert(heapToCreate[0]);

        assertThrowsExactly(IllegalArgumentException.class, ()->heap.increaseValue(first,0));


        for (int i = 1; i < heapToCreate.length; i++) {
            heap.insert(heapToCreate[i]);
        }
        int newValue = Arrays.stream(heapToCreate).max().getAsInt() + 10;
        heap.increaseValue(first,  newValue);
        assertEquals(newValue, heap.maximum().value);

    }

    @org.junit.jupiter.api.Test
    void extractMax() {
        Arrays.stream(heapToCreate).forEach(num -> heap.insert(num));
        Arrays.stream(heapToCreate)
                .boxed()
                .sorted(Collections.reverseOrder())
                .forEach(i ->
                    assertEquals(i, heap.extractMax())
                );


    }

    @org.junit.jupiter.api.Test
    void union() {

        Integer[] heap1nums = new Integer[] {7,9,22,12,40,32,21,33,35,31,28,37,50,26,43,44,    2,25,    19};
        Integer[] heap2nums = new Integer[] { 14,29,23,42,  6,20,  11 };

        Arrays.stream(heap1nums).forEach(num -> heap.insert(num));

        var heap2 = new BinomialHeap<>(Integer::compareTo);
        Arrays.stream(heap2nums).forEach(num -> heap2.insert(num));

        heap.union(heap2);

        var list = new ArrayList<Integer>();

        Collections.addAll(list, heap1nums);
        Collections.addAll(list, heap2nums);
        list.stream()
                .sorted(Collections.reverseOrder())
                .forEach(o -> assertEquals(o, heap.extractMax()));

    }
}