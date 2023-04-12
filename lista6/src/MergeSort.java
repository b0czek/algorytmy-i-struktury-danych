import core.SortingAlgorithm;

import java.util.*;

public class MergeSort<T> extends SortingAlgorithm<T> {
    public MergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }


    private List<T> merge(List<T> list, List<T> merge1, List<T> merge2) {
        List<T> result = list instanceof LinkedList ? new LinkedList<>() : new ArrayList<>();

        Iterator<T> l = merge1.iterator();
        Iterator<T> r = merge2.iterator();

        boolean hadNextL, hadNextR;
        T elemL = null, elemR = null;

        if(hadNextL = l.hasNext()) {
            elemL = l.next();
        }
        if(hadNextR = r.hasNext()) {
            elemR = r.next();
        }


        while(hadNextL && hadNextR) {
            if(compare(elemL, elemR) <= 0) {
                addLast(result, elemL);
                if(hadNextL = l.hasNext()) {
                    elemL = l.next();
                } else {
                    addLast(result, elemR);
                }
            } else {
                addLast(result, elemR);
                if(hadNextR = r.hasNext()) {
                    elemR = r.next();
                } else {
                    addLast(result, elemL);
                }
            }

        }
        while(l.hasNext()) {
            addLast(result, l.next());
        }
        while(r.hasNext()) {
            addLast(result, r.next());
        }
        return result;

    }

    @Override
    public List<T> sort(List<T> list) {

        LinkedList<List<T>> merges = new LinkedList<>(list.stream().map(e -> {
            List<T> l = e instanceof LinkedList ? new LinkedList<>() : new ArrayList<>();
            addLast(l, e);
            return l;
        }).toList());
        while(merges.size() > 1) {
            List<T> merge1 = merges.removeFirst();
            List<T> merge2 = merges.removeFirst();
            addLast((List<T>) merges, (T) this.merge(list, merge1, merge2));
        }

        return merges.removeFirst();
    }








}
