public class PimpedQueue<T> {
    private final DoublyLinkedList<T> list;

    public PimpedQueue() {
        list = new DoublyLinkedList<>();
    }

    public PimpedQueue(T[] original) {
        list = new DoublyLinkedList<>(original);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.getSize();
    }

    public void pushLast(T value) {
        list.addLast(value);
    }

    public T popFirst() {
        return list.popFirst();
    }

    public T peekFirst() {
        return list.getFirst();
    }

    public String toString() {
        return list.toString();
    }
}
