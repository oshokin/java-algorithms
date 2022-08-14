import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyHashTable<K, V> {
    private static final int DEFAULT_HASH_TABLE_SIZE = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Object[] table;
    private int tableRehashingCounter;
    private float loadFactor;
    private int size;

    public MyHashTable() {
        this(DEFAULT_LOAD_FACTOR);
    }

    public MyHashTable(float loadFactor) {
        this.table = new Object[DEFAULT_HASH_TABLE_SIZE];
        this.loadFactor = loadFactor;

        updateTableRehashingCounter();
    }

    public void put(K key, V value) {
        int hash = getInnerHashCode(key.hashCode());
        var pairs = getBucket(hash);
        if (pairs == null) pairs = new LinkedList<>();

        pairs.add(new MyEntry(key, value));
        table[hash] = pairs;

        //STOP! REHASHING TIME!!!
        if (++size == tableRehashingCounter) rehashTable();
    }

    public V get(K key) {
        int hash = getInnerHashCode(key.hashCode());
        var pairs = getBucket(hash);
        if (pairs == null) return null;
        for (MyEntry pair : pairs) {
            if (Objects.equals(pair.key, key)) return pair.value;
        }

        return null;
    }

    public boolean delete(K key) {
        int hash = getInnerHashCode(key.hashCode());
        var pairs = getBucket(hash);
        if (pairs == null) return false;

        var result = pairs.removeIf(e -> Objects.equals(e.key, key));
        if (pairs.size() == 0) pairs = null;
        table[hash] = pairs;
        if (result) size--;

        return result;
    }

    private int getInnerHashCode(int code) {
        return getInnerHashCode(code, table.length);
    }

    private int getInnerHashCode(int code, int tableSize) {
        return Math.abs(code) % tableSize;
    }

    private void rehashTable() {
        var newTable = new Object[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            var bucket = getBucket(i);
            if (bucket == null) continue;

            for (var entry : bucket) {
                int hash = getInnerHashCode(entry.key.hashCode(), newTable.length);
                var pairs = getBucket(hash);
                if (pairs == null) pairs = new LinkedList<>();

                pairs.add(entry);
                newTable[hash] = pairs;
            }
        }

        table = newTable;
        updateTableRehashingCounter();
    }

    private void updateTableRehashingCounter() {
        this.tableRehashingCounter = (int) ((float) table.length * this.loadFactor);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var isFirstEntry = true;
        for (int i = 0; i < table.length; i++) {
            var bucket = getBucket(i);
            if (bucket == null || bucket.size() == 0) {
                continue;
            }

            for (MyEntry entry : bucket) {
                if (!isFirstEntry) {
                    sb.append(", ");
                }

                isFirstEntry = false;
                sb.append(entry);
            }
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private List<MyEntry> getBucket(int index) {
        return (List<MyEntry>) table[index];
    }

    private class MyEntry {
        private final K key;
        private final V value;

        private MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyEntry myEntry = (MyEntry) o;
            return key.equals(myEntry.key) && value.equals(myEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return String.format("%s: %s", key, value);
        }
    }
}
