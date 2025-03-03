import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public interface SimpleList<E> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        return new SimpleArrayList<T>(arrays);
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        double sum = 0;

        for(int i=0;i<values.size();i++){
            T currentValue = values.get(i);
            sum += currentValue.doubleValue();
        }

        return sum;
    }

    boolean add(E element);

    void add(int index, E element);

    E set(int index, E element);

    E get(int index);

    boolean contains(E element);

    int indexOf(E element);

    int size();

    boolean isEmpty();

    boolean remove(E element);

    E remove(int index);

    void clear();
}
