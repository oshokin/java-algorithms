import java.util.*;

public class DoublyLinkedList<T> {
    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return this.next;
        }

        public Node<E> getPrev() {
            return this.prev;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public String toString() {
            return "Node(value=" + this.getValue() + ", next=" + this.getNext() + ", prev=" + this.getPrev() + ")";
        }
    }

    private class DoublyLinkedListIterator implements Iterator<T> {
        private Node<T> cursor;
        private Node<T> next;
        private int nextIndex;

        // хеш считать долго и дорого при каждом изменении связанного списка,
        // поэтому пусть будет счетчик изменений
        private int changesCountAtCreation;

        DoublyLinkedListIterator(int index) {
            next = (index == size) ? null : getNode(index);
            nextIndex = index;
            changesCountAtCreation = changesCount;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            checkListForConsistency();
            if (!hasNext()) throw new NoSuchElementException("Что с лицом, псина?!");
            cursor = next;
            next = next.getNext();
            nextIndex++;

            return cursor.getValue();
        }

        public void remove() {
            checkListForConsistency();
            if (cursor == null) throw new IllegalStateException("Азязязя! Вы кого слушали то?");
            var lastNext = cursor.getNext();
            removeNode(cursor);
            if (next == cursor) next = lastNext;
            else nextIndex--;
            cursor = null;
            changesCountAtCreation++;
        }

        private void checkListForConsistency() {
            if (changesCount != changesCountAtCreation)
                throw new RuntimeException("Кто-то научился в параллелизм?! А, щенок?!");
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    private int changesCount = 0;

    public DoublyLinkedList() {
    }

    public DoublyLinkedList(T[] items) {
        this();
        for (T item : items) {
            addLast(item);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int idx) {
        return getNode(idx).getValue();
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size - 1);
    }

    public void addFirst(T value) {
        if (size > 0) {
            var node = new Node<>(value, null, head);
            head.setPrev(node);
            head = node;
        } else setHeadValue(value);
        size++;
        changesCount++;
    }

    public void addLast(T value) {
        if (size > 0) {
            var node = new Node<>(value, tail, null);
            tail.setNext(node);
            tail = node;
        } else setHeadValue(value);
        size++;
        changesCount++;
    }

    public void insert(int idx, T value) {
        var node = getNode(idx);
        var prevNode = node.getPrev();
        var addedNode = new Node<>(value, prevNode, node);
        if (prevNode != null) prevNode.setNext(addedNode);
        node.setPrev(addedNode);
        size++;
        changesCount++;
    }

    public void removeFirst() {
        checkListBounds();
        var nextNode = head.getNext();
        if (nextNode != null) {
            nextNode.setPrev(null);
            head = nextNode;
        }
        size--;
        changesCount++;
        cleanPointers();
    }

    public void removeLast() {
        checkListBounds();
        var prevNode = tail.getPrev();
        if (prevNode != null) {
            prevNode.setNext(null);
            tail = prevNode;
        }
        size--;
        changesCount++;
        cleanPointers();
    }

    public T popFirst() {
        checkListBounds();
        var value = head.getValue();
        removeFirst();

        return value;
    }

    public T popLast() {
        checkListBounds();
        var value = tail.getValue();
        removeLast();

        return value;
    }

    public void remove(int idx) {
        var node = getNode(idx);
        removeNode(node);
    }

    public String toString() {
        if (size == 0) return "[]";
        var current = head;
        var sb = new StringBuilder(size);
        sb.append("[");
        do {
            var value = current.getValue();
            sb.append(value);
            current = current.getNext();
            if (current == null) sb.append("]");
            else sb.append(" ");
        } while (current != null);

        return sb.toString();
    }

    public Iterator<T> getIterator(int idx) {
        return new DoublyLinkedListIterator(idx);
    }

    private void cleanPointers() {
        if (size == 0) {
            head = null;
            tail = null;
        }
    }

    private void setHeadValue(T value) {
        head = new Node<>(value, null, null);
        tail = head;
    }

    private Node<T> getNode(int idx) {
        if (idx > size - 1) throw new IndexOutOfBoundsException("Да ты шо, псина! Тикай из городу!");
        if (idx == 0) return head;
        if (idx == size - 1) return tail;
        var current = head;
        for (int i = 0; i <= idx; i++) {
            if (i > 0) current = current.getNext();
            if (i == idx) break;
        }

        return current;
    }

    private void removeNode(Node<T> node) {
        var prevNode = node.getPrev();
        var nextNode = node.getNext();
        if (prevNode != null) prevNode.setNext(nextNode);
        else head = nextNode;
        if (nextNode != null) nextNode.setPrev(prevNode);
        else tail = nextNode;
        size--;
        changesCount++;
        cleanPointers();
    }

    private void checkListBounds() {
        if (size == 0) throw new RuntimeException("Нельзя просто взять и удОлить то, чего нет!");
    }
}
