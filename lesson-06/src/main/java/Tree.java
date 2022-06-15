public class Tree {
    private TreeNode root;

    public Tree() {
        root = null;
    }

    public void insert(Cat c) {
        TreeNode node = new TreeNode(c);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (c.age < current.cat.age) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = node;
                        return;
                    }
                } else if (c.age > current.cat.age) {
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

    public Cat find(int age) {
        TreeNode current = root;
        while (current.cat.age != age) {
            if (age < current.cat.age)
                current = current.leftChild;
            else
                current = current.rightChild;

            if (current == null)
                return null;
        }

        return current.cat;
    }

    private void inOrderTravers(TreeNode current) {
        if (current != null) {
            System.out.println(current);
            inOrderTravers(current.leftChild);
            inOrderTravers(current.rightChild);
        }
    }

    public void displayTree() {
        inOrderTravers(root);
    }

    public boolean delete(int age) {
        TreeNode curr = root;
        TreeNode prev = root;
        boolean isLeftChild = true;
        while (curr.cat.age != age) {
            prev = curr;
            if (age < curr.cat.age) {
                isLeftChild = true;
                curr = curr.leftChild;
            } else {
                isLeftChild = false;
                curr = curr.rightChild;
            }

            if (curr == null)
                return false;
        }

        if (curr.leftChild == null && curr.rightChild == null) {
            if (curr == root) {
                root = null;
            } else if (isLeftChild) {
                prev.leftChild = null;
            } else {
                prev.rightChild = null;
            }
        } else if (curr.rightChild == null) {
            if (isLeftChild) {
                prev.leftChild = curr.leftChild;
            } else {
                prev.rightChild = curr.leftChild;
            }
        } else if (curr.leftChild == null) {
            if (isLeftChild) {
                prev.leftChild = curr.rightChild;
            } else {
                prev.rightChild = curr.rightChild;
            }
        } else {
            TreeNode successor = getSuccessor(curr);
            if (curr == root) {
                root = successor;
            } else if (isLeftChild) {
                prev.leftChild = successor;
            } else {
                prev.rightChild = successor;
            }
            successor.leftChild = curr.leftChild;
        }

        return true;
    }

    private TreeNode getSuccessor(TreeNode deleted) {
        TreeNode successorParent = deleted;
        TreeNode successor = deleted;
        TreeNode flag = deleted.rightChild;

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

    public class TreeNode {
        public TreeNode leftChild;
        public TreeNode rightChild;
        private Cat cat;

        public TreeNode(Cat cat) {
            this.cat = cat;
        }

        @Override
        public String toString() {
            return String.format("TN(%s)", cat);
        }
    }
}