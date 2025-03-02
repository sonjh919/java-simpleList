import static jdk.internal.util.ArraysSupport.newLength;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArrayList implements SimpleList{

    private static final String[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;

    protected int modCount = 0;
    private int size;
    String[] elementData;

    public SimpleArrayList() {
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * modCount란?
     * 1. 컬렉션의 구조적 변경 감지
     * 2. 동시 수정 감지 -> 동시성 관리 -> 안정성 보장
     */
    @Override
    public boolean add(String value) {
        modCount++;
        add(value, elementData, size);
        return true;
    }

    private void add(String e, String[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }
    
    private String[] grow() {
        return grow(size + 1);
    }

    private String[] grow(int minCapacity) {
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

    @Override
    public void add(int index, String value) {
        rangeCheckForAdd(index); // index 범위 검증
        modCount++;
        final int s;
        Object[] elementData;

        if ((s = size) == (elementData = this.elementData).length) // 배열 크기확인, 늘리기
            elementData = grow();

        System.arraycopy(elementData, index, // 추가
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
    }

    @Override
    public String set(int index, String value) {
        Objects.checkIndex(index, size);
        String oldValue = elementData[index];
        elementData[index] = value;
        return oldValue; // 이전 값 반환
    }

    @Override
    public String get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) >=0; // indexOf: 리스트에 있으면 인덱스, 없으면 -1 반환
    }

    @Override
    public int indexOf(String value) {
        return indexOfRange(value, 0, size);
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
    public boolean remove(String value) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        found: { // 객체 찾기: 못찾으면 false 리턴
            if (value == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (value.equals(es[i]))
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
    public String remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") String oldValue = (String) es[index];
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
