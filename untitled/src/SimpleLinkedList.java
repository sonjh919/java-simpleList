public class SimpleLinkedList implements SimpleList{
    int size = 0;
    private int modCount = 0;
    Node<String> first;
    Node<String> last;

    private void linkFirst(String s){
        final Node<String> f = first;
        final Node<String> newNode = new Node<>(null, s, f);
        first = newNode;
        if(f == null) last = newNode;
        else f.prev = newNode;
        size++;
        modCount++;
    }

    void linkLast(String s){
        final Node<String> l = last;
        final Node<String> newNode = new Node(l, s, null);
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    @Override
    public boolean add(String value) {
        linkLast(value);
        return true;
    }

    @Override
    public void add(int index, String value) {
    }

    @Override
    public String set(int index, String value) {
        return "";
    }

    @Override
    public String get(int index) {
        return "";
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(String value) {
        return 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(String value) {
        return false;
    }

    @Override
    public String remove(int index) {
        return "";
    }

    @Override
    public void clear() {

    }

    private static class Node<String>{
        String item;
        Node<String> next;
        Node<String> prev;

        public Node(Node<String> prev, String item, Node<String> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
