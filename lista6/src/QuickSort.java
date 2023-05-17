import core.SortingAlgorithm;

import java.util.*;

public class QuickSort<T> extends SortingAlgorithm<T> {


    private List<T> dummyList = new ArrayList();


    private PivotSelector<T> pivotSelector;
    public QuickSort(Comparator<? super T> comparator, String name, PivotSelector<T> pivotSelector) {
        super(comparator);
        this.algorithmName = name;
        this.pivotSelector = pivotSelector;

    }

    private void swap(ListIterator<T> iter1, ListIterator<T> iter2, T value1, T value2) {
        swap(dummyList, 0, 0);
        iter1.set(value2);
        iter2.set(value1);
    }
    private T swap(ListIterator<T> iter1, T value1, List<T> list, int pos) {
        T value2 = list.set(pos, value1);
        iter1.set(value2);
        swap(dummyList, 0,0);
        return value2;
    }

    private int partition(List<T> list, int left, int right) {
        if (left == right) {
            return left;
        }
        ListIterator<T> iterator = list.listIterator(left);
        ListIterator<T> current = list.listIterator(left + 1);

        int pivot = pivotSelector.selectPivot(list, left, right);
        T pivotValue = iterator.next();
        if(pivot != left) {
            swap(iterator, pivotValue, list, pivot);
        }

        while(iterator.hasNext()) {
            T value = iterator.next();
            if(compare(value, pivotValue) < 0) {
                swap(iterator, current, value, current.next());
            }
        }
        int previous = current.previousIndex();
        if(previous != left) {
            swap(current, current.previous(), list, left);
        }
        return previous;
    }

    public void quicksort(List<T> list, int startIdx, int endIdx){
        dummyList = new ArrayList<>();
        dummyList.add(list.get(0));

        if(endIdx > startIdx) {
            int partition = partition(list, startIdx, endIdx);
            quicksort(list, startIdx, partition);
            quicksort(list, partition + 1, endIdx);
        }
    }

    @Override
    public List<T> sort(List<T> list) {
        quicksort(list, 0, list.size());
        return list;
    }







    public interface PivotSelector<T> {
        int selectPivot( List<T> list, int left, int right);
    }
}
