import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class QuickSort<T> extends SortingAlgorithm<T> {





    private PivotSelector<T> pivotSelector;
    public QuickSort(Comparator<? super T> comparator, String name, PivotSelector<T> pivotSelector) {
        super(comparator);
        this.algorithmName = name;
        this.pivotSelector = pivotSelector;

    }

    private int partition(List<T> list, int left, int right) {
        if(left == right) {
            return left;
        }
        int pivot = pivotSelector.selectPivot(list, left, right);
        if(pivot != left) {
            swap(list, left, pivot);
        }
        T pivotValue = list.get(left);

        RangeIterator<T> iterator = new RangeIterator<>(left + 1, right, list.iterator());
        RangeIterator<T> current = new RangeIterator<>(left + 1, right, list.iterator());
        int prevPos = left;

        while(iterator.hasNext()) {
            T value = iterator.next();
            if(compare(value, pivotValue) < 0) {
                swap(list, iterator.getPos() - 1, current.getPos());
                prevPos = current.getPos();
                current.next();
            }
        }
        if(prevPos != left) {
            swap(list, left, prevPos);
        }
        return prevPos;
    }


    public void quicksort(List<T> list, int startIdx, int endIdx){
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
