public class Deque extends Queue {
    public Deque(int size) {
        super(size);
    }

    public Deque(int size, byte lf) {
        super(size, lf);
    }

    public Deque(int[] original) {
        super(original);
    }

    public Deque(int[] original, byte lf) {
        super(original, lf);
    }

    public void pushFirst(int i) {
        grow();
        if (items > 0) {
            if (head == 0) head = maxSize;
            head--;
        } else tail = 0;

        queue[head] = i;
        items++;
    }

    public int popLast() {
        if (items == 0) throw new RuntimeException("а что так можно было?");
        if (--tail < 0) tail = maxSize - 1;
        int temp = queue[tail];
        if (--items == 0) {
            head = 0;
            tail = -1;
        }

        return temp;
    }

    public int peekLast() {
        if (items == 0) throw new RuntimeException("давай без фокусов, псина!");
        return queue[tail];
    }
}