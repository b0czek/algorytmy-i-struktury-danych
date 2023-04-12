package core;

import java.util.Comparator;
import java.util.List;

import measuring.CountingAdder;
import measuring.CountingComparator;
import measuring.CountingSwapper;

public abstract class SortingAlgorithm<T> {
	
	private CountingComparator<T> comparator;
	private CountingSwapper<T> swapper;
	private CountingAdder<T> adder;
	
	public SortingAlgorithm(Comparator<? super T> comparator) {
		this.comparator = new CountingComparator<T>(comparator);
		swapper = new CountingSwapper<>();
		this.adder = new CountingAdder<>();
	}
	
	public void reset() {
		comparator.reset();
		swapper.reset();
		adder.reset();
	}
	
	public long comparisons() {
		return comparator.count();
	}
	
	public long swaps() {
		return swapper.count();
	}

	public long adds() {
		return adder.count();
	}
	
	public Comparator<? super T> baseComparator() {
		return comparator.baseComparator();
	}
	
	protected int compare(T lhs, T rhs) {
		return comparator.compare(lhs, rhs);
	}
	
	protected void swap(List<T> list, int index1, int index2) {
		swapper.swap(list, index1, index2);
	}

	protected void addLast(List<T> list, T element) {
		adder.addLast(list, element);
	}

	public abstract List<T> sort(List<T> list);

	public String algorithmName = this.getClass().getSimpleName();

}
