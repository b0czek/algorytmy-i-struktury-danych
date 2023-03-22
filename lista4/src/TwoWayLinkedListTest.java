import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class OneWayLinkedListWithSentinelTest {
    private OneWayLinkedListWithSentinel<Integer> list;



    private void add10IntegersToList() {
        for(int i = 0; i < 10; i ++) {
            list.add(i);
        }
    }
    private Object[] pushArray(Object[] array, Object value) {
        Object[] newArray = new Integer[array.length + 1];
        System.arraycopy(array, 0 , newArray, 0, array.length);
        newArray[array.length] = value;
        return newArray;
    }


    @BeforeEach
    public void init() {
        list = new OneWayLinkedListWithSentinel<>();
    }

    @Test
    void asArray() {
        assertArrayEquals(new Integer[]{}, list.asArray());
        this.add10IntegersToList();
        assertArrayEquals(IntStream.range(0,10).boxed().toArray(), list.asArray());
    }

    @org.junit.jupiter.api.Test
    void add() {
        Object[] arr = list.asArray();
        for(int i = 0 ; i < 10; i++) {

            arr = pushArray(arr, i);
            this.list.add(i);
            assertArrayEquals(arr, list.asArray());
        }
        arr = pushArray(arr, null);
        this.list.add(null);
        assertArrayEquals(arr, list.asArray());

    }

    @org.junit.jupiter.api.Test
    void testAdd() {
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(1, 5));
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(-1, 5));
        list.add(0, 0);
        assertArrayEquals(new Integer[]{0}, list.asArray());

        list.clear();
        this.add10IntegersToList();
        list.add(10, null);
        assertArrayEquals(new Integer[]{0,1,2,3,4,5,6,7,8,9,null}, list.asArray());


        list.add(10, 10);
        assertArrayEquals(new Integer[]{0,1,2,3,4,5,6,7,8,9,10,null}, list.asArray());


    }

    @org.junit.jupiter.api.Test
    void clear() {
        list.clear();
        assertEquals(0,list.size());

        this.add10IntegersToList();
        list.clear();
        assertEquals(0, list.size());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        assertFalse(list.contains(null));
        assertFalse(list.contains(2));

        this.add10IntegersToList();

        assertFalse(list.contains(29));
        assertTrue(list.contains(9));
        assertFalse(list.contains(null));

        list.add(null);
        assertTrue(list.contains(null));
    }

    @org.junit.jupiter.api.Test
    void get() {
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(0));
        this.add10IntegersToList();
        for(int i = 0 ; i < 10 ; i ++) {
            assertEquals(i, list.get(i));
        }
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(-1)) ;
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(10)) ;

    }

    @org.junit.jupiter.api.Test
    void set() {
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.set(0, null));

        this.add10IntegersToList();

        list.set(9, null);
        assertArrayEquals(new Integer[]{0,1,2,3,4,5,6,7,8,null}, list.asArray());

        list.set(0, 10);

        assertArrayEquals(new Integer[]{10,1,2,3,4,5,6,7,8,null}, list.asArray());



    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        assertEquals(-1, list.indexOf(null));
        assertEquals(-1, list.indexOf(5));

        this.add10IntegersToList();
        assertEquals(-1, list.indexOf(16));
        assertEquals(-1, list.indexOf(null));
        for(int i= 0; i < 10; i++) {

            assertEquals(i, list.indexOf(i));
        }
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        this.add10IntegersToList();
        assertFalse(list.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        assertFalse(list.remove(null));
        assertFalse(list.remove(Integer.valueOf(5)));

        this.add10IntegersToList();
        assertFalse(list.remove(null));
        assertFalse(list.remove(Integer.valueOf(15)));

        assertTrue(list.remove(Integer.valueOf(5)));
        assertArrayEquals(new Integer[]{0,1,2,3,4,6,7,8,9}, list.asArray());

    }

    @org.junit.jupiter.api.Test
    void testRemove() {
        assertThrowsExactly(IndexOutOfBoundsException.class, ()->list.remove(0));

        this.add10IntegersToList();
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(-1)) ;
        assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(10)) ;

        assertEquals(5, list.remove(5));
        assertArrayEquals(new Integer[] {0,1,2,3,4,6,7,8,9}, list.asArray());

    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(0, list.size());

        this.add10IntegersToList();
        assertEquals(10, list.size());
    }


    @Test
    void isPalindrome() {
        assertTrue(list.isPalindrome());
        this.add10IntegersToList();
        assertFalse(list.isPalindrome());


        this.list.clear();
        this.list.add(1);
        assertTrue(list.isPalindrome());
        this.list.add(2);
        assertFalse(list.isPalindrome());
        this.list.add(1);
        assertTrue(list.isPalindrome());

        this.list.clear();
        this.list.add(1);
        this.list.add(2);
        this.list.add(2);
        this.list.add(1);
        assertTrue(list.isPalindrome());

        this.list.add(2);
        this.list.add(2);
        this.list.add(1);
        assertTrue(list.isPalindrome());

    }

}