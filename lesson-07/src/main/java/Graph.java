import java.util.*;

public class Graph<T> {
    private List<Vertex> vertices;
    private List<List<Boolean>> links;
    private Deque<Integer> deque;
    private Map<String, Integer> indexesByLabel;

    public Graph(int initialCapacity) {
        vertices = new ArrayList<>(initialCapacity);
        links = new ArrayList<>(initialCapacity);
        deque = new ArrayDeque<>();
        indexesByLabel = new HashMap<>(initialCapacity);

        var emptyBooleanList = new ArrayList<Boolean>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            emptyBooleanList.add(false);
        }

        for (int i = 0; i < initialCapacity; i++) {
            links.add(new ArrayList<>(emptyBooleanList));
        }
    }

    public void depthFirstSearch() {
        if (vertices.size() == 0) {
            System.out.println("Surprise, motherfucker! Graph is empty");
            return;
        }

        visitVertexByIndex(0, false);
        while (!deque.isEmpty()) {
            int v = getFirstUnvisitedVertexIndex(deque.peekFirst());
            if (v == -1) deque.pop();
            else visitVertexByIndex(v, false);
        }

        vertices.forEach(v -> v.isVisited = false);
    }

    public void breadthFirstSearch() {
        if (vertices.size() == 0) {
            System.out.println("Surprise, motherfucker! Graph is empty");
            return;
        }

        visitVertexByIndex(0, true);
        int neighbourIdx;
        while (!deque.isEmpty()) {
            int v = deque.getLast();
            while ((neighbourIdx = getFirstUnvisitedVertexIndex(v)) != -1) {
                visitVertexByIndex(neighbourIdx, true);
            }
        }

        vertices.forEach(v -> v.isVisited = false);
    }

    public void addVertex(String label, T value) {
        var vertex = new Vertex(label, value);
        indexesByLabel.put(label, vertices.size());
        vertices.add(vertex);
    }

    public void addEdge(String startLabel, String endLabel) {
        int startIndex = getIndexByLabel(startLabel);
        int endIndex = getIndexByLabel(endLabel);

        var startLinks = links.get(startIndex);
        startLinks.set(endIndex, true);

        var endLinks = links.get(endIndex);
        endLinks.set(startIndex, true);
    }

    public T getValueByLabel(String label) {
        var idx = getIndexByLabel(label);
        return vertices.get(idx).value;
    }

    private void visitVertexByIndex(int idx, boolean isBFS) {
        var vertex = vertices.get(idx);
        vertex.isVisited = true;

        var isDequeEmpty = (deque.size() == 0);
        if (!isDequeEmpty) System.out.print(" -> ");

        if (isBFS) deque.addLast(idx);
        else deque.addFirst(idx);

        System.out.println(vertex);
    }

    private Integer getIndexByLabel(String label) {
        var idx = indexesByLabel.get(label);
        if (idx == null) throw new IllegalArgumentException(String.format("vertex \"%s\" is not found", label));

        return idx;
    }

    private int getFirstUnvisitedVertexIndex(int vertexIndex) {
        var vertexLinks = links.get(vertexIndex);
        for (int i = 0; i < vertices.size(); i++) {
            if (vertexLinks.get(i) && !vertices.get(i).isVisited) {
                return i;
            }
        }

        return -1;
    }

    private class Vertex {
        private String label;
        private T value;
        private boolean isVisited;

        public Vertex(String label, T value) {
            this.label = label;
            this.value = value;
            this.isVisited = false;
        }

        @Override
        public String toString() {
            return String.format("%s (value: %s)", label, value);
        }
    }
}
