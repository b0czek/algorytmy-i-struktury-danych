import java.util.Arrays;

public class Zad2 {

    private static final int[][] testCases = new int[][]{
            {},
            {6},
            {1, 2, 3},
            {-1, -2, -3},
            {-1, 1, -2, 2},
            {1, 2, 8, -3, -3, -4, 7, 10, 0},
            {7, 7, 7, 7, 8, 2, 5, 5, 5, 10},
            {1, 2, 3, 4, 5, 2, 3, 4, -1, -2, -3, -4, 245, 245, 245, 566}
    };
    private static final int[][][] expectedOutput = new int[][][]{
            {{}},
            {{6}},
            {{1, 2, 3}},
            {{-1}, {-2}, {-3}},
            {{-1, 1}, {-2, 2}},
            {{1, 2, 8}, {-3, -3}, {-4, 7, 10}, {0}},
            {{7, 7, 7, 7, 8}, {2, 5, 5, 5, 10}},
            {{1, 2, 3, 4, 5}, {2, 3, 4}, {-1}, {-2}, {-3}, {-4, 245, 245, 245, 566}}

    };
    private static final Substring[] expectedOutput2 = new Substring[]{
            new Substring(-1, 0),
            new Substring(0, 1),
            new Substring(0, 3),
            new Substring(0, 1),
            new Substring(0, 2),
            new Substring(0, 3),
            new Substring(0, 5),
            new Substring(0, 5)

    };

    private static int[][] growArray(int[][] array) {
        int[][] newArray = new int[array.length + 1][];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private static int[][] pushArray(int[][] array, int[] element) {
        int[][] arr = growArray(array);
        arr[array.length] = element;
        return arr;
    }

    public static int[][] longestNondecreasingSubstrings(int[] numbers) {
        if (numbers.length <= 1) {
            return new int[][]{numbers};
        }
        int[][] result = new int[0][];
        int startIdx = 0;


        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                result = pushArray(result, Arrays.copyOfRange(numbers, startIdx, i));
                startIdx = i;
            }
        }
        result = pushArray(result, Arrays.copyOfRange(numbers, startIdx, numbers.length));

        return result;
    }

    public static Substring longestNondecreasingSubstring(int[] numbers) {
        if (numbers.length == 0) {
            return new Substring(-1, 0);
        }

        int maxPos = 0;
        int maxLength = 1;

        int pos = 0;
        int length = 1;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                if(length > maxLength) {
                    maxPos = pos;
                    maxLength = length;
                }
                pos = i + 1;
                length = 1;
            }
            else {
                length++;
            }
        }
        if(length > maxLength) {
            maxPos = pos;
            maxLength = length;
        }

        return new Substring(maxPos, maxLength);

    }

    public static void main(String[] args) {
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("input: " + Arrays.toString(testCases[i]));
            {
                var result = longestNondecreasingSubstrings(testCases[i]);
                System.out.println("test1 result: " + Arrays.deepToString(result));
                boolean valid = Arrays.deepEquals(result, expectedOutput[i]);
                System.out.println(valid ? "pass" : "fail");
            }

            {
                var result = longestNondecreasingSubstring(testCases[i]);
                System.out.println("test2 result: " + result);
                boolean valid = result.equals(expectedOutput2[i]);
                System.out.println(valid ? "pass" : "fail");
                System.out.println();
            }
        }
    }
}
