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

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        SimpleList<T> tempList = new SimpleArrayList<>();
        for(int i=0;i<values.size();i++){
            if(values.get(i).doubleValue()>=0){
                tempList.add(values.get(i));
            }
        }
        return tempList;
    }

    // PECS는 'Producer-extends, Consumer-super'의 약자. 생산하는 쪽은 extends를, 소비하는 쪽은 super를 설정하자.
    static <T> void copy(SimpleList<? extends T> laserPrinters, SimpleList<? super T> printers) {
        for(int i=0;i<laserPrinters.size();i++){
            printers.add(laserPrinters.get(i));
        }
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
