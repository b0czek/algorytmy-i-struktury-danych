import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    private static final int[] nums = IntStream.range(0, 30).toArray();

    private static final int[] division =
            {0, 1, 2 ,3 ,4 ,0 , 5, 6, 7, 2};

    public static void main(String[] args) {
        Array2<Integer> array = new Array2<>(division);
        var numsIterator = Arrays.stream(nums).iterator();

        for(int i = 0; i < division.length; i++) {
            for(int j = 0; j < division[i]; j++) {
                array.set(numsIterator.hasNext() ? numsIterator.next() : 0, i, j);
            }
        }

        for(int elem : array) {
            System.out.print(elem + " ");
        }

    }
}