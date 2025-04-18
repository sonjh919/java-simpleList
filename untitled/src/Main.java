import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        List<String> a = new ArrayList<>();
        List<String> b = new LinkedList<>();
        List<Integer> c = new ArrayList<>();

        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        // mission 2
        final String[] arrays = {"first", "second"};
        final SimpleList<String> values2 = SimpleList.<String>fromArrayToList(arrays);

        // mission 3
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        // mission 4
        final SimpleList<Double> doubleValues2 = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues2 = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues2);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues2);


        // mission 5
        class Printer { }
        class LaserPrinter extends Printer { }

        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter); // true

    }
}