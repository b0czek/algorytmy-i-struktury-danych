package tester;

import sets.ISet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Tester<T> {

    private Supplier<ISet<T>>[] setFactories;
    private Function<Integer, List<T>> testSequenceSupplier;

    public Tester(Supplier<ISet<T>>[] setFactories, Function<Integer, List<T>> testSequenceSupplier) {
        this.setFactories = setFactories;
        this.testSequenceSupplier = testSequenceSupplier;
    }

    public List<Result> test(int setSize, int repetitions) {
        List<Result> results = new ArrayList<>();
        for(Supplier<ISet<T>> factory : setFactories) {
            double averageInsertTime = 0.0;
            double averageInsertTimeSquared = 0.0;

            double averageSearchTime = 0.0;
            double averageSearchTimeSquared = 0.0;

            double averageRemoveTime = 0.0;
            double averageRemoveTimeSquared = 0.0;

            for(int j = 1 ; j <= repetitions; j++) {
                RunResult result = run(factory, setSize);
                averageInsertTime = updatedAverage(averageInsertTime, result.insertTime, j);
                averageSearchTime = updatedAverage(averageSearchTime, result.searchTime, j);
                averageRemoveTime = updatedAverage(averageRemoveTime, result.removeTime, j);
                averageInsertTimeSquared = updatedAverage(averageInsertTimeSquared,
                        (double)result.insertTime * (double)result.insertTime, j);
                averageSearchTimeSquared = updatedAverage(averageSearchTimeSquared,
                        (double)result.searchTime * (double)result.searchTime, j);
                averageRemoveTimeSquared = updatedAverage(averageRemoveTimeSquared,
                        (double)result.removeTime * (double)result.removeTime, j);
            }

            results.add(new Result(factory.get().getName(), setSize,
                    averageInsertTime, calculateStdDev(averageInsertTime, averageInsertTimeSquared),
                    averageSearchTime, calculateStdDev(averageSearchTime, averageSearchTimeSquared),
                    averageRemoveTime, calculateStdDev(averageRemoveTime, averageRemoveTimeSquared)
            ));
        }
        return results;
    }

    private static double updatedAverage(double average, double value, int n) {
        return average + (value - average) / n;
    }

    private static double calculateStdDev(double average, double averagedSquares) {
        return Math.sqrt(averagedSquares - (average * average));
    }



    private RunResult run(Supplier<ISet<T>> factory, int n) {
        ISet<T> set = factory.get();
        List<T> sequence = testSequenceSupplier.apply(n);

        Timer insertTimer = new Timer();
        insertTimer.start();
        for(T element : sequence) {
            set.insert(element);
        }
        insertTimer.stop();

        Timer searchTimer = new Timer();
        searchTimer.start();
        for(T element: sequence) {
            boolean result = set.contains(element);
            assert result;
        }
        searchTimer.stop();

        Collections.shuffle(sequence);
        Timer removeTimer = new Timer();
        removeTimer.start();
        for(T element: sequence) {
            boolean result = set.remove(element);
            assert result;
        }
        removeTimer.stop();


        return new RunResult(insertTimer.durationMillis(), searchTimer.durationMillis(), removeTimer.durationMillis());

    }

}
