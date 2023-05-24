package tester;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntSupplier {
    public static List<Integer> shuffled(int n) {
        var range = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Collections.shuffle(range);
        return range;
    }
}
