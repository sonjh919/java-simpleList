import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        List<String> a = new ArrayList<>();
        List<String> b = new LinkedList<>();

        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        // mission 2
        final String[] arrays = {"first", "second"};
        final SimpleList<String> values2 = SimpleList.<String>fromArrayToList(arrays);


    }
}