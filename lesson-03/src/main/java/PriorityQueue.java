public class PriorityQueue extends Queue {
    public PriorityQueue(int size) {
        super(size);
    }

    public PriorityQueue(int size, byte lf) {
        super(size, lf);
    }

    public PriorityQueue(int[] original) {
        super(original);
    }

    public PriorityQueue(int[] original, byte lf) {
        super(original, lf);
    }

    public void pushLast(int value) {
        if (items == 0) {
            queue[++tail] = value;
            items++;
            return;
        }

        grow();
        int nextIdx;
        boolean itWasBigger;
        for (int i = tail; ; i--) {
            if (i < 0) i = maxSize - 1;
            nextIdx = i + 1;
            if (nextIdx == maxSize) nextIdx = 0;
            queue[nextIdx] = queue[i];
            itWasBigger = queue[i] < value;
            if ((value > queue[i] && itWasBigger) || (nextIdx == head)) {
                break;
            }
        }

        queue[nextIdx] = value;
        items++;
        if (++tail == maxSize) tail = 0;
    }
}