package tester;

import sets.BSTSet;
import sets.ISet;
import sets.RBSet;
import sets.SkipList;

import java.util.Comparator;
import java.util.function.Supplier;

public class SetFactories {
    public static <T> Supplier<ISet<T>>[] getFactories(Comparator<T> comparator) {
        return new Supplier[] {
                () -> new BSTSet<T>(comparator),
                () -> new RBSet<T>(comparator),
//                () -> new SkipList<T>(comparator, 0.0),
                () -> new SkipList<T>(comparator, 0.25),
                () -> new SkipList<T>(comparator, 0.5),
                () -> new SkipList<T>(comparator, 0.8),

        };
    }
}
