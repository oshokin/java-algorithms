import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class TreeResearcher {
    private List<Tree<Integer>> trees;
    private SecureRandom randGen;
    private int treesCount;

    public TreeResearcher(int treesCount) {
        trees = new ArrayList<>(treesCount);
        randGen = new SecureRandom();
        this.treesCount = treesCount;
    }

    public void randomizeTrees(int numsCount, int start, int end) {
        for (int i = 0; i < treesCount; i++) {
            var tree = new Tree<Integer>("Дерево " + (i + 1));
            for (int j = 0; j < numsCount; j++) {
                tree.insert(randGen.nextInt(start, end + 1));
            }
            trees.add(tree);
        }
    }

    public void showUnbalancedTreesCount() {
        System.out.println("А сейчас, пёс, давай посчитаем сколько у нас несбалансированных деревьев!!!");
        System.out.printf("Всего деревьев: %d\n", treesCount);
        var unbalancedTreesCount = 0;
        for (Tree<Integer> tree : trees) {
            if (!tree.isBalanced()) unbalancedTreesCount++;
        }
        var unbalancedTreesPercent = unbalancedTreesCount * 100 / treesCount;
        System.out.printf("Несбалансированных деревьев: %d, то есть %d %% от общего количества\n", unbalancedTreesCount, unbalancedTreesPercent);
    }

    public void showTrees() {
        trees.forEach(Tree::display);
    }

    public void showTreesDepth() {
        trees.forEach(Tree::displayDepth);
    }
}