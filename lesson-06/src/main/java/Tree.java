public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private String name;

    public Tree(String name) {
        this.root = null;
        this.name = name;
    }

    public void insert(T value) {
        var node = new TreeNode<>(value);
        if (root == null) root = node;
        else {
            TreeNode<T> current = root;
            TreeNode<T> parent;
            while (true) {
                parent = current;
                var comparisionResult = value.compareTo(current.value);
                if (comparisionResult < 0) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = node;
                        return;
                    }
                } else if (comparisionResult > 0) {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = node;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public T find(T value) {
        var current = root;
        while (!current.value.equals(value)) {
            if (value.compareTo(current.value) < 0) current = current.leftChild;
            else current = current.rightChild;

            if (current == null) return null;
        }

        return current.value;
    }

    public void display() {
        System.out.printf("Начало дерева \"%s\"\n", name);
        traverseInOrder(root);
        System.out.printf("Конец дерева \"%s\"\n", name);
    }

    public void displayDepth() {
        // Лень заморачиваться с форматированным выводом, а ля, one, few, many :)
        System.out.printf("Глубина дерева \"%s\" = %d уровень (-ня, -ней)\n", name, getDepth());
    }

    public boolean delete(T value) {
        TreeNode<T> curr = root;
        TreeNode<T> prev = root;
        boolean isLeftChild = true;
        while (curr.value.compareTo(value) != 0) {
            prev = curr;
            if (value.compareTo(curr.value) < 0) {
                isLeftChild = true;
                curr = curr.leftChild;
            } else {
                isLeftChild = false;
                curr = curr.rightChild;
            }

            if (curr == null) return false;
        }

        if (curr.leftChild == null && curr.rightChild == null) {
            if (curr == root) root = null;
            else if (isLeftChild) prev.leftChild = null;
            else prev.rightChild = null;
        } else if (curr.rightChild == null) {
            if (isLeftChild) prev.leftChild = curr.leftChild;
            else prev.rightChild = curr.leftChild;
        } else if (curr.leftChild == null) {
            if (isLeftChild) prev.leftChild = curr.rightChild;
            else prev.rightChild = curr.rightChild;
        } else {
            var successor = getSuccessor(curr);
            if (curr == root) root = successor;
            else if (isLeftChild) prev.leftChild = successor;
            else prev.rightChild = successor;

            successor.leftChild = curr.leftChild;
        }

        return true;
    }

    public int getDepth() {
        return getDepth(root);
    }

    public boolean isBalanced() {
        return getDepth(root.leftChild) == getDepth(root.rightChild);
    }

    private int getDepth(TreeNode<T> current) {
        if (current == null) return 0;
        return 1 + Math.max(getDepth(current.leftChild), getDepth(current.rightChild));
    }

    private void traverseInOrder(TreeNode<T> current) {
        traverseInOrder(current, 0);
    }

    private void traverseInOrder(TreeNode<T> current, int level) {
        if (current == null) return;
        if (current != root) {
            System.out.print(" -> ");
        }
        System.out.print(current);
        System.out.printf(", lvl: %d", level + 1);

        traverseInOrder(current.leftChild, level + 1);
        traverseInOrder(current.rightChild, level + 1);
    }

    private TreeNode<T> getSuccessor(TreeNode<T> deleted) {
        var successorParent = deleted;
        var successor = deleted;
        var flag = deleted.rightChild;

        while (flag != null) {
            successorParent = successor;
            successor = flag;
            flag = flag.leftChild;
        }
        if (successor != deleted.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = deleted.rightChild;
        }

        return successor;
    }

    public static class TreeNode<T> {
        private final T value;
        public TreeNode<T> leftChild;
        public TreeNode<T> rightChild;

        public TreeNode(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("tree-node(%s)", value);
        }
    }
}