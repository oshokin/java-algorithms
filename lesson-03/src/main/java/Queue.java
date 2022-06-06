public class Queue {
    protected static final byte defaultLoadFactor = 2;

    protected int maxSize;        // размер
    protected byte loadFactor;    // коэффициент увеличения размера
    protected int[] queue;        // место хранения
    protected int head;           // отсюда уходят
    protected int tail;           // сюда приходят
    protected int items;          // текущее количество

    public Queue(int size) {
        this(size, defaultLoadFactor);
    }

    public Queue(int size, byte lf) {
        maxSize = size;
        loadFactor = lf;
        queue = new int[maxSize];
        head = 0;
        tail = -1;
        items = 0;
    }

    public Queue(int[] original) {
        this(original, defaultLoadFactor);
    }

    public Queue(int[] original, byte lf) {
        loadFactor = lf;
        if (loadFactor <= 0) loadFactor = defaultLoadFactor;
        maxSize = original.length;
        var copy = new int[maxSize];
        System.arraycopy(original, 0, copy, 0, maxSize);

        loadFactor = lf;
        queue = copy;
        head = 0;
        tail = maxSize - 1;
        items = maxSize;
    }

    public boolean isEmpty() {
        return (items == 0);
    }

    public boolean isFull() {
        return (items == maxSize);
    }

    public int size() {
        return items;
    }

    public void pushLast(int i) {
        grow();
        if (tail == maxSize - 1) tail = -1;
        queue[++tail] = i;
        ++items;
    }

    public int popFirst() {
        if (items == 0) throw new RuntimeException("Something got broken definitely!");
        int temp = queue[head++];
        if (head >= maxSize) head = 0;
        if (--items == 0) {
            head = 0;
            tail = -1;
        }

        return temp;
    }

    public int peekFirst() {
        if (items == 0) throw new RuntimeException("Oye, que estas haciendo mi helmanico?");
        return queue[head];
    }

    public String toString() {
        if (items == 0) return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = head; ; i++) {
            if (i == maxSize) i = 0;
            b.append(queue[i]);
            if (i == tail) {
                b.append(']');
                break;
            }
            b.append(", ");
        }

        return b.toString();
    }

    protected void grow() {
        if (!isFull()) return;
        maxSize *= loadFactor;
        int[] tmpArr = new int[maxSize];
        if (tail >= head) {
            System.arraycopy(queue, 0, tmpArr, 0, queue.length);
        } else {
            System.arraycopy(queue, 0, tmpArr, 0, tail + 1);
            var newHead = maxSize - (queue.length - head);
            System.arraycopy(queue, head, tmpArr, newHead, queue.length - head);
            head = newHead;
        }
        queue = tmpArr;
    }
}