public class Main {
    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>(Integer::compareTo, 0.5);
        skipList.insert(1);

        System.out.println(skipList.contains(1));
    }
}