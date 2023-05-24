import sets.BSTSet;
import sets.SkipList;
import tester.IntSupplier;
import tester.Result;
import tester.SetFactories;
import tester.Tester;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Tester tester = new Tester<>(SetFactories.getFactories(Integer::compare), IntSupplier::shuffled);
        Map<String, ArrayList<Result>> results = new HashMap<>();
        for (int i = 10; i <= 10000 ; i *= 10) {
            for (int j = 2; j <= 10; j++) {
                int size = i * j;
                List<Result> result = tester.test(size, 20);
                for(Result r : result) {
                    if(results.get(r.getSetName()) == null) {
                        results.put(r.getSetName(), new ArrayList<>());
                    }
                    results.get(r.getSetName()).add(r);
                }


                System.out.println(size);
            }
        }
        for(var set : results.entrySet()) {
            try {
                FileWriter fw = new FileWriter(set.getKey() + ".csv");
                fw.write(csvHeader);
                for(var result : set.getValue()) {
                    String str = String.format("%s;%d;%.12f;%.12f;%.12f;%.12f;%.12f;%.12f\n",
                            result.getSetName(),
                            result.getSize(),
                            result.getAverageInsertTime(),
                            result.getInsertStdDev(),
                            result.getAverageSearchTime(),
                            result.getSearchStdDev(),
                            result.getAverageRemoveTime(),
                            result.getRemoveStdDev()
                    );
                    fw.write(str);
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static String csvHeader = "name;size;avgInsert;insertStdDev;avgSearch;searchStdDev;avgRemove;removeStdDev\n";
}