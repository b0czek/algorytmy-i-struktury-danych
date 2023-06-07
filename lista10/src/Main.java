import Sety.DisjointSet;
import Sety.ForestDisjointSet;
import Sety.ListDisjointSet;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        DisjointSet[] sets = new DisjointSet[]{
                new ListDisjointSet(),
                new ForestDisjointSet()
        };
        int n = 10000000;
        for (DisjointSet set : sets) {
            Object[] arr = new Object[n];
            Timer timer = new Timer();
            timer.start();
            for (int i = 0; i < n; i++) {
                arr[i] = set.makeSet();
            }
            timer.stop();
            System.out.println(set.getClass().getSimpleName() + " makeset: " + timer.durationMillis() + "ms");

            timer.reset();
            timer.start();
            for(int i = 1 ; i < n; i++) {
                set.union(arr[i-1],arr[i]);
            }
            timer.stop();
            System.out.println("union: " + timer.durationMillis() + "ms");

            timer.reset();
            LinkedList<Object> elemsToUnite = new LinkedList<>(IntStream.range(0,n).mapToObj(i -> arr[i]).collect(Collectors.toList()));


            timer.start();
            while(elemsToUnite.size() > 1) {
                Object obj1 = elemsToUnite.removeFirst();
                Object obj2 = elemsToUnite.removeFirst();
                set.union(obj1, obj2);
                elemsToUnite.add(obj1);

            }
            timer.stop();
            System.out.println("findset: " + timer.durationMillis() + "ms");

        }
    }
}
