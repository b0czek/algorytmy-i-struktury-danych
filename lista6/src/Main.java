
import java.io.*;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Function;

import core.SortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {
	private static final Random random = new Random();

	private static final int maxValue = 1000;

	private static final Generator<Integer>[] generators = new Generator[] {
				new OrderedIntegerArrayGenerator(),
				new ReversedIntegerArrayGenerator(),
			new ShuffledIntegerArrayGenerator(),
			new RandomIntegerArrayGenerator(maxValue),
	};
	private static final Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<>(new IntegerComparator());

	private static final SortingAlgorithm<MarkedValue<Integer>>[] algorithms = new SortingAlgorithm[] {
//			new MergeSort(markedComparator),
//			new QuickSort(markedComparator, "QuickSort-FirstElement", (list, left, right) -> left),
			new QuickSort(markedComparator, "QuickSort-RandomElement", (list, left, right) -> random.nextInt(right - left) + left),
	};

	private static final Function<Generator<Integer>, Generator<MarkedValue<Integer>>>[] conversions = new Function[] {
			(generator) -> new MarkingGenerator<Integer>((Generator<Integer>)generator),
			(generator) -> new LinkedListGenerator<MarkedValue<Integer>>(new MarkingGenerator<>((Generator<Integer>) generator))
	};

	public static void main(String[] args) throws IOException {


		FileWriter fw = new FileWriter("result.txt");
		fw.write(csvHeader);
		for(var algorithm : algorithms) {
			int f = algorithm instanceof QuickSort ? 1000 : 100000;
			for(var conversion: conversions) {
				for (var generator : generators) {
					for (int i = 10; i <= f ; i *= 10) {
						for (int j = 2; j <= 10; j++) {
							int size = i * j;
							var c = conversion.apply(generator);
							Result result = Tester.runNTimes(algorithm, c, size, 20);
							writeResult(fw, algorithm, c, generator, size, result);

						}
					}
				}
			}
		}
		fw.close();
	}

	private static final String csvHeader = "algorithm;generator;size;avg_time;time_deviation;avg_comparisons;comparisons_deviation;avg_swaps;swaps_deviation;avg_adds;adds_deviation;sorted;stable;conversion\n";


	private static <T,W,V> void writeResult(FileWriter fw, SortingAlgorithm<T> algorithm, Generator<W> conversion, Generator<V> generator, int size, Result result) throws IOException {
		String output = String.format("%s;%s;%d;%.12f;%.12f;%.12f;%.12f;%.12f;%.12f;%.12f;%.12f;%s;%s;%s\n",
				algorithm.algorithmName,
				generator.getClass().getSimpleName(),
				size,
				result.averageTimeInMilliseconds(),
				result.timeStandardDeviation(),
				result.averageComparisons(),
				result.comparisonsStandardDeviation(),
				result.averageSwaps(),
				result.swapsStandardDeviation(),
				result.averageAdds(),
				result.addsStandardDeviation(),
				result.sorted() ? "true" : "false",
				result.stable() ? "true" : "false",
				conversion.getClass().getSimpleName()

			);
		System.out.print(output);
		fw.write(output);
	}

}
