package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P637 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null)
            return result;

        Set<TreeNode> curr = new HashSet<>();
        Set<TreeNode> next = new HashSet<>();
        curr.add(root);

        double sum;
        while (curr.size() > 0) {
            sum = 0;
            for (TreeNode node : curr) {
                sum += node.val;
                if (node.left != null)
                    next.add(node.left);
                if (node.right != null)
                    next.add(node.right);
            }
            result.add(sum / curr.size());
            curr.clear();
            Set<TreeNode> tmp = curr;
            curr = next;
            next = tmp;
        }
        return result;
    }

}
