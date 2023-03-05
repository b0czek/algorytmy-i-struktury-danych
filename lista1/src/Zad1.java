public class Zad1 {
    private static final char PADDING_CHAR = ' ';
    private static final char BODY_CHAR = 'X';
    private static final char PATTERN_CHAR = 'O';
    private static void printRepeated(char c, int count) {
        for(int i = 0 ; i < count; i ++) {
            System.out.print(c);
        }
    }

    private static int calcZigZagPattern(int pos, int max) {
        if(max == 0) {
            return 0;
        }
        return max - Math.abs((pos % (2 * max)) - max);
    }

    private static void printPadding(int pos, int maxPadding) {
        int paddingCount = calcZigZagPattern(pos, maxPadding);
        printRepeated(PADDING_CHAR, paddingCount);
    }
    private static void printPattern(int pos, int k) {
        int width = 2 * k + 1;
        int patternChars = Math.max(2 * calcZigZagPattern(pos, k - 1) + 1, 1);
        int bodyCharsPerSide = (width - patternChars) / 2;

        printRepeated(BODY_CHAR, bodyCharsPerSide);
        printRepeated(PATTERN_CHAR, patternChars);
        printRepeated(BODY_CHAR, bodyCharsPerSide);

    }

    private static void drawZigZaw(int n, int l) {
        for(int i = 0; i < n; i ++) {
            printPadding(i, l - 1);
            printRepeated(BODY_CHAR, l);
            System.out.println();
        }
    }
    private static void drawScarf(int n, int k) {
        for(int i = 0; i < n; i ++) {
            printPadding(i, 2*k);
            printPattern(i, k);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++)  {
            System.out.println("zigzag l = " + i);
            drawZigZaw(10, i);

        }
        for(int i = 0; i < 5; i++)  {
            System.out.println("scarf k = " + i);
            drawScarf(10, i);

        }
    }
}