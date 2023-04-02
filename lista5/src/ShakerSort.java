import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ShakerSort<T> extends SortingAlgorithm<T> {
    public ShakerSort(Comparator<? super T> comparator) {
        super(comparator);
    }


    private boolean pass(List<T> list, int start, Predicate<Integer>condition, int increment) {
        boolean swapped = false;
        for(int i = start; condition.test(i); i += increment) {
            if(compare(list.get(i), list.get(i +1)) > 0) {
                swap(list, i , i+1);
                swapped = true;
            }
        }
        return swapped;
    }

    @Override
    public List<T> sort(List<T> list) {
        int left = 0;
        int right = list.size();


        boolean swapped = true;
        while(swapped) {
            int r = right;
            if(!pass(list, left, i -> (i < r - 1),1)) {
                break;

            }
            right--;

            swapped = pass(list, right - 1, i -> i>=0, -1);

            left++;
        }

        return list;
    }
}
