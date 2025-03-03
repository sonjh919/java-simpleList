public interface SimpleList<E> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        return new SimpleArrayList<T>(arrays);
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
