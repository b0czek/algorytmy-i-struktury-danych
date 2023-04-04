
import java.io.*;
import java.util.Comparator;
import java.util.function.Function;

import core.SortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

	private static final int maxValue = 1000;

	private static final Generator<Integer>[] generators = new Generator[] {
			new OrderedIntegerArrayGenerator(),
//			new ReversedIntegerArrayGenerator(),
//			new ShuffledIntegerArrayGenerator(),
//			new RandomIntegerArrayGenerator(maxValue),
	};
	private static final Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<>(new IntegerComparator());

	private static final SortingAlgorithm<MarkedValue<Integer>>[] algorithms = new SortingAlgorithm[] {
			new BinaryInsertionSort(markedComparator),
//			new MinmaxSort(markedComparator),
//			new ShakerSort(markedComparator)

	};

	private static final Function<Generator<Integer>, Generator<Integer>>[] conversions = new Function[] {
			(generator) -> new MarkingGenerator<Integer>((Generator<Integer>)generator),
			(generator) -> new LinkedListGenerator<Integer>((Generator<Integer>)generator)
	};

	public static void main(String[] args) throws IOException {


		DataOutputStream dos = new DataOutputStream(new FileOutputStream("result.txt"));
		dos.writeUTF(csvHeader);
		for(var algorithm : algorithms) {
			for (var generator : generators) {
				for (int i = 10; i <= 10000; i *= 10) {
					for (int j = 2; j <= 10; j++) {
						int size = i * j;
						Result result = Tester.runNTimes(algorithm, new MarkingGenerator<>(generator), size, 20);
						writeResult(dos, algorithm, generator, size, result);

					}
				}
			}
		}
		dos.close();
	}

	private static final String csvHeader = "algorithm;generator;size;avg_time;time_deviation;avg_comparisons;comparisons_deviation;avg_swaps;swaps_deviation;sorted;stable\n";


	private static <T,V> void writeResult(DataOutputStream dos, SortingAlgorithm<T> algorithm, Generator<V> generator, int size, Result result) throws IOException {
		String output = String.format("%s;%s;%d;%.12f;%.12f;%.12f;%.12f;%.12f;%.12f;%s;%s\n",
				algorithm.getClass().getSimpleName(),
				generator.getClass().getSimpleName(),
				size,
				result.averageTimeInMilliseconds(),
				result.timeStandardDeviation(),
				result.averageComparisons(),
				result.comparisonsStandardDeviation(),
				result.averageSwaps(),
				result.swapsStandardDeviation(),
				result.sorted() ? "true" : "false",
				result.stable() ? "true" : "false"
			);
		System.out.print(output);
		dos.writeUTF(output);
	}

}
