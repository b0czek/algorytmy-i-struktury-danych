import core.SortingAlgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinmaxSort<T> extends SortingAlgorithm<T> {
    public MinmaxSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        for(int i = 0, j = list.size() - 1; i < j; i++, j--) {
            T min, max;
            min = max = list.get(i);

            int min_idx , max_idx;
            min_idx = max_idx = i;

            for(int k = i; k <= j; k++) {
                T value = list.get(k);
                if(compare(value, max) > 0) {
                    max = value;
                    max_idx = k;
                }
                else if(compare(value, min) < 0) {
                    min = value;
                    min_idx = k;
                }
            }

            swap(list, min_idx, i);
            if(list.get(min_idx) == max) {
                swap(list, min_idx, j);
            }
            else {
                swap(list, max_idx, j);

            }
        }
        return list;
    }
}
