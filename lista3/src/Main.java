
public class Main {
    public static void main(String[] args) {

        OneWayLinkedListWithSentinel<Integer> list = new  OneWayLinkedListWithSentinel<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);


        System.out.println(list.isPalindrome());


    }
}