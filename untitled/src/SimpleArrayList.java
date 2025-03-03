
import java.util.Arrays;
import java.util.Objects;

public class SimpleArrayList<E> implements SimpleList<E>{

    private static final String[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;

    public static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;

    private int modCount = 0;
    private int size;
    Object[] elementData;

    public SimpleArrayList() {
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public <T> SimpleArrayList(T[] arrays) {
        elementData = arrays;
    }

    public SimpleArrayList(E v1, E v2) {
        elementData = new Object[]{v1,v2};
    }


    /**
     * modCount란?
     * 1. 컬렉션의 구조적 변경 감지
     * 2. 동시 수정 감지 -> 동시성 관리 -> 안정성 보장
     */
    @Override
    public boolean add(E element) {
        modCount++;
        add(element, elementData, size);
        return true;
    }

    private void add(E element, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = element;
        size = s + 1;
    }
    
    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) { // 기존 배열이 비어있지 않거나 기본 빈 배열이 아닌 경우
            int newCapacity = newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new String[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    public static int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // preconditions not checked because of inlining
        // assert oldLength >= 0
        // assert minGrowth > 0

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            // put code cold in a separate method
            return hugeLength(oldLength, minGrowth);
        }
    }

    private static int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
            return SOFT_MAX_ARRAY_LENGTH;
        } else {
            return minLength;
        }
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index); // index 범위 검증
        modCount++;
        final int s;
        Object[] elementData;

        if ((s = size) == (elementData = this.elementData).length) // 배열 크기확인, 늘리기
            elementData = grow();

        System.arraycopy(elementData, index, // 추가
                elementData, index + 1,
                s - index);
        elementData[index] = element;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue; // 이전 값 반환
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >=0; // indexOf: 리스트에 있으면 인덱스, 없으면 -1 반환
    }

    @Override
    public int indexOf(E element) {
        return indexOfRange(element, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] es = elementData;
        if (o == null) { // null일때 찾기
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else { // null아닐때 찾기
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean remove(E element) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found: { // 객체 찾기: 못찾으면 false 리턴
            if (element == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (element.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(es, i); // 찾으면 지우고 true 리턴
        return true;
    }

    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        // 제거할 요소 이후의 모든 요소를 한 칸씩 앞으로 이동
        es[size = newSize] = null;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") E oldValue = (E) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    @Override
    public void clear() {
        modCount++;
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }
}
