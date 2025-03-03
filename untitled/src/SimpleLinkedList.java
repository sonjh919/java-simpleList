//public class SimpleLinkedList implements SimpleList {
//    int size = 0;
//    private int modCount = 0;
//    Node<String> first;
//    Node<String> last;
//
//    private void linkFirst(String s) {
//        final Node<String> f = first;
//        final Node<String> newNode = new Node<>(null, s, f);
//        first = newNode;
//        if (f == null) {
//            last = newNode;
//        } else {
//            f.prev = newNode;
//        }
//        size++;
//        modCount++;
//    }
//
//    void linkLast(String s) {
//        final Node<String> l = last;
//        final Node<String> newNode = new Node<>(l, s, null);
//        if (l == null) {
//            first = newNode;
//        } else {
//            l.next = newNode;
//        }
//        size++;
//        modCount++;
//    }
//
//    void linkBefore(String s, Node<String> succ) {
//        // assert succ != null;
//        final Node<String> pred = succ.prev;
//        final Node<String> newNode = new Node<>(pred, s, succ);
//        succ.prev = newNode;
//        if (pred == null) {
//            first = newNode;
//        } else {
//            pred.next = newNode;
//        }
//        size++;
//        modCount++;
//    }
//
//    String unlink(Node<String> x) {
//        // assert x != null;
//        final String element = x.item;
//        final Node<String> next = x.next;
//        final Node<String> prev = x.prev;
//
//        if (prev == null) {
//            first = next;
//        } else {
//            prev.next = next;
//            x.prev = null;
//        }
//
//        if (next == null) {
//            last = prev;
//        } else {
//            next.prev = prev;
//            x.next = null;
//        }
//
//        x.item = null;
//        size--;
//        modCount++;
//        return element;
//    }
//
//    @Override
//    public boolean add(String value) {
//        linkLast(value);
//        return true;
//    }
//
//    @Override
//    public void add(int index, String value) {
//        if (index == size) {
//            linkLast(value);
//        } else {
//            linkBefore(value, node(index));
//        }
//    }
//
//    @Override
//    public String set(int index, String value) {
//        Node<String> x = node(index);
//        String oldVal = x.item;
//        x.item = value;
//        return oldVal;
//    }
//
//    @Override
//    public String get(int index) {
//        return node(index).item;
//    }
//
//    Node<String> node(int index) {
//        // assert isElementIndex(index);
//
//        if (index < (size >> 1)) {
//            Node<String> x = first;
//            for (int i = 0; i < index; i++) {
//                x = x.next;
//            }
//            return x;
//        } else {
//            Node<String> x = last;
//            for (int i = size - 1; i > index; i--) {
//                x = x.prev;
//            }
//            return x;
//        }
//    }
//
//    @Override
//    public boolean contains(String value) {
//        return indexOf(value) >= 0;
//    }
//
//    @Override
//    public int indexOf(String value) {
//        int index = 0;
//        if (value == null) {
//            for (Node<String> x = first; x != null; x = x.next) {
//                if (x.item == null) {
//                    return index;
//                }
//                index++;
//            }
//        } else {
//            for (Node<String> x = first; x != null; x = x.next) {
//                if (value.equals(x.item)) {
//                    return index;
//                }
//                index++;
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return first.next == null && last.prev == null;
//    }
//
//    @Override
//    public boolean remove(String value) {
//        if (value == null) {
//            for (Node<String> x = first; x != null; x = x.next) {
//                if (x.item == null) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        } else {
//            for (Node<String> x = first; x != null; x = x.next) {
//                if (value.equals(x.item)) {
//                    unlink(x);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public String remove(int index) {
//        final Node<String> node = node(index);
//        String oldVal = node.item;
//        node.prev = null;
//        node.item = null;
//        node.next = null;
//        return oldVal;
//    }
//
//    @Override
//    public void clear() {
//        for (Node<String> x = first; x != null; ) {
//            Node<String> next = x.next;
//            x.item = null;
//            x.next = null;
//            x.prev = null;
//            x = next;
//        }
//        first = last = null;
//        size = 0;
//        modCount++;
//    }
//
//    private static class Node<String> {
//        String item;
//        Node<String> next;
//        Node<String> prev;
//
//        public Node(Node<String> prev, String item, Node<String> next) {
//            this.item = item;
//            this.next = next;
//            this.prev = prev;
//        }
//    }
//}
