import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class BinaryInsertionSort<T> extends SortingAlgorithm<T> {
    public BinaryInsertionSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    private int binarySearch(List<T> list, T element, int l, int r) {
        while(l <= r) {
            int m = (l+r) / 2;
            int compareResult = compare(element, list.get(m));
            if(compareResult < 0) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }

    @Override
    public List<T> sort(List<T> list) {

        for(int i = 1; i < list.size(); i++) {
            int j = i - 1;
            T value = list.get(i);
            int pos = binarySearch(list, value, 0, j);

            while(j >= pos) {
                swap(list, j + 1, j);
                j--;
            }

        }

        return list;
    }
}
