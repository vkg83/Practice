import java.util.*;

public class Test {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> array =
                new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<10000; i++) {
            try {
                array.add(new ArrayList<Integer>(1000000));
            } catch (OutOfMemoryError e) {
                System.out.println(i);
                break;
            }
        }
    }
}