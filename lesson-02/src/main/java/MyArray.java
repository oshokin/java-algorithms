import java.util.TreeMap;

public class MyArray {
    private final int loadFactor;
    private int capacity;
    private int[] arr;

    //new int[5];
    public MyArray(int size) {
        this.capacity = 0;
        this.loadFactor = 2;
        this.arr = new int[size];
    }

    // = {1,2,3,4,5};
    public MyArray(int[] init) {
        this.capacity = init.length;
        this.loadFactor = 2;
        this.arr = init;
    }

    public int getCapacity() {
        return capacity;
    }

    public void display() {
        for (int i = 0; i < this.capacity; ++i) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public int get(int idx) {
        return arr[idx];
    }

    public void set(int value, int idx) {
        arr[idx] = value;
    }

    public boolean delete(int value) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i] == value) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                --capacity;
                return true;
            }
        }
        return false;
    }

    public boolean deleteAll(int value) {
        int curPos = 0;
        boolean isFound = false;
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i] == value) {
                isFound = true;
                continue;
            }

            if (curPos < i) {
                this.arr[curPos] = this.arr[i];
            }

            curPos++;
        }

        if (isFound) {
            this.capacity = curPos;
        }

        return isFound;
    }

    public void clear(boolean isErasureRequired) {
        if (isErasureRequired) {
            for (int i = 0; i < capacity; i++) {
                this.arr[i] = 0;
            }
        }

        this.capacity = 0;
    }

    public void insert(int value, int pos) {
        if (pos == this.capacity) {
            append(value);
            return;
        }

        if (pos + 1 > this.capacity) {
            this.capacity = pos + 1;
        }
        grow();
        System.arraycopy(arr, pos, arr, pos + 1, this.capacity - pos);
        this.capacity++;
        this.arr[pos] = value;
    }

    public void append(int value) {
        grow();
        this.arr[this.capacity++] = value;
    }

    public boolean isInArray(int value) { // O(n)
        for (int i = 0; i < this.capacity; i++)
            if (this.arr[i] == value)
                return true;
        return false;
    }

    //O(log(N))
    public boolean hasValue(int value) {
        int low = 0;
        int high = this.capacity - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (value == this.arr[mid]) {
                return true;
            } else {
                if (value < this.arr[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        return false;
    }

    public int bubbleSort() {
        if (capacity < 2) return 0;
        var iterationsCount = 0;
        for (int iter = 0; iter < capacity; iter++) {
            for (int idx = 0; idx < capacity - 1; idx++) {
                if (this.arr[idx] > this.arr[idx + 1]) {
                    swapIndexes(idx, idx + 1);
                }

                iterationsCount++;
            }
        }

        return iterationsCount;
    }

    public int optimizedBubbleSort() {
        if (capacity < 2) return 0;
        var iterationsCount = 0;
        for (int iter = 0; iter < capacity; iter++) {
            //фиксируем факт перестановки на каждом шае
            boolean wereIndexesSwapped = false;
            //"пузырек" уже прошел все, что > capacity - iter - 1
            for (int idx = 0; idx < capacity - iter - 1; idx++) {
                if (this.arr[idx] > this.arr[idx + 1]) {
                    swapIndexes(idx, idx + 1);
                    wereIndexesSwapped = true;
                }

                iterationsCount++;
            }

            //если все и так упорядочено, выходим:
            if (!wereIndexesSwapped) break;
        }

        return iterationsCount;
    }


    public int selectionSort() {
        if (capacity < 2) return 0;
        var iterationsCount = 0;
        for (int idx = 0; idx < capacity; idx++) {
            int curr = idx;
            for (int srch = idx + 1; srch < capacity; srch++) {
                if (this.arr[srch] < this.arr[curr]) curr = srch;
                iterationsCount++;
            }

            if (curr != idx) {
                swapIndexes(idx, curr);
            }
        }

        return iterationsCount;
    }

    public int insertionSort() {
        if (capacity < 2) return 0;
        var iterationsCount = 0;
        for (int curr = 1; curr < capacity; curr++) {
            int temp = this.arr[curr];
            int move = curr;
            while (move > 0 && this.arr[move - 1] >= temp) {
                this.arr[move] = this.arr[move - 1];
                iterationsCount++;
                move--;
            }

            this.arr[move] = temp;
        }

        return iterationsCount;
    }

    //делал без гугла, самое сложное - упорядочить ключи map'ы по значению хеш-функции,
    //т. к. порядок не гарантирован.
    //в оригинальном алгоритме это делается через создание массива размером макс. кол-ва элементов,
    //по сути тот же HashMap, где hashKey == индекс в массиве...
    //пусть не самый правильный вариант, но прикольно же :)
    public int treeMapCountingSort() {
        if (capacity < 2) return 0;
        var iterationsCount = 0;
        var counter = new TreeMap<Integer, Integer>();
        for (int idx = 0; idx < capacity; idx++) {
            var value = this.arr[idx];
            var count = counter.getOrDefault(value, 0);
            counter.put(value, ++count);
            iterationsCount++;
        }

        int idx = 0;
        for (var entry : counter.entrySet()) {
            var value = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                this.arr[idx++] = value;
            }
            iterationsCount++;
        }

        return iterationsCount;
    }

    //взял реализацию из гугла, для сравнения
    public int wikiCountingSort() {
        if (capacity < 2) return 0;
        var iterationsCount = 0;
        int max = arr[0];
        for (int idx = 1; idx < capacity; idx++) {
            if (arr[idx] > max) max = arr[idx];
            iterationsCount++;
        }
        int[] count = new int[max + 1];
        for (int idx = 0; idx < capacity; idx++) {
            count[arr[idx]]++;
            iterationsCount++;
        }
        for (int idx = 1; idx <= max; idx++) {
            count[idx] += count[idx - 1];
        }
        int[] output = new int[capacity + 1];
        for (int i = capacity - 1; i >= 0; i--) {
            var value = arr[i];
            output[count[value] - 1] = value;
            count[value]--;
            iterationsCount++;
        }
        this.arr = output;

        return iterationsCount;
    }

    //попробовал через массив, мне не очень нравится вариант,
    //потребление памяти под массив зависит от максимального и минимального элементов.
    public int arrayCountingSort() {
        if (capacity < 2) return 0;
        var iterationsCount = 0;
        var min = arr[0];
        var max = arr[0];
        for (int idx = 1; idx < capacity; idx++) {
            if (arr[idx] < min) min = arr[idx];
            if (arr[idx] > max) max = arr[idx];
            iterationsCount++;
        }
        var counterSize = Math.abs(min) + max + 1;
        int[] counter = new int[counterSize];
        for (int idx = 0; idx < capacity; idx++) {
            var value = arr[idx];
            counter[value - min]++;
            iterationsCount++;
        }
        int[] output = new int[capacity];
        int curPos = 0;
        for (int idx = 0; idx < counterSize; idx++) {
            var count = counter[idx];
            if (count == 0) {
                iterationsCount++;
                continue;
            }
            var value = idx + min;
            for (int i = 0; i < count; i++) {
                output[curPos] = value;
                curPos++;
                iterationsCount++;
            }
        }
        this.arr = output;

        return iterationsCount;
    }

    private void grow() {
        if (this.capacity == this.arr.length) {
            int[] old = this.arr;
            this.arr = new int[old.length * loadFactor];
            System.arraycopy(old, 0, arr, 0, old.length);
        }
    }

    private void swapIndexes(int a, int b) {
        int tmp = this.arr[a];
        this.arr[a] = this.arr[b];
        this.arr[b] = tmp;
    }
}