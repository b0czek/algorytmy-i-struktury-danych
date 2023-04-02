
import java.util.Comparator;
import java.util.function.Function;

import core.SortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

	private static final int maxValue = 100;

	private static final Generator<Integer>[] generators = new Generator[] {
			new OrderedIntegerArrayGenerator(),
			new ReversedIntegerArrayGenerator(),
			new ShuffledIntegerArrayGenerator(),
			new RandomIntegerArrayGenerator(maxValue),
	};
	private static final Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<>(new IntegerComparator());

	private static final SortingAlgorithm<MarkedValue<Integer>>[] algorithms = new SortingAlgorithm[] {
			new BinaryInsertionSort(markedComparator),
			new MinmaxSort(markedComparator),
			new ShakerSort(markedComparator)

	};

	private static final Function<Generator<Integer>, Generator<Integer>>[] conversions = new Function[] {
			(generator) -> new MarkingGenerator<Integer>((Generator<Integer>)generator),
			(generator) -> new LinkedListGenerator<Integer>((Generator<Integer>)generator)
	};

	public static void main(String[] args) {


		for(var algorithm : algorithms) {
			for (var generator : generators) {
				for (int i = 10; i <= 100000; i *= 10) {
					for (int j = 1; j < 10; j++) {
						Result result = Tester.runNTimes(algorithm, new MarkingGenerator<>(generator), i * j, 20);
						printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
						printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
						printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

						System.out.println("always sorted: " + result.sorted());
						System.out.println("always stable: " + result.stable());
					}
				}
			}
		}
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<>(new IntegerComparator());

		Generator<MarkedValue<Integer>> generator = new MarkingGenerator<>(new RandomIntegerArrayGenerator(100));

		SortingAlgorithm<MarkedValue<Integer>> algorithm = new BinaryInsertionSort<>(markedComparator);
		
		Result result = Tester.runNTimes(algorithm, generator, 1000, 20);
		
		printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
		printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
		printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());
		
		System.out.println("always sorted: " + result.sorted());
		System.out.println("always stable: " + result.stable());
	}

//	private static void

	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}
	
	private static String double2String(double value) {
		return String.format("%.12f", value);
	}

}
