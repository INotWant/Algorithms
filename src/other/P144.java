package other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P144 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    /*
    // 方法一：递归
    private void helper(TreeNode root, List<Integer> result) {
        if (null != root) {
            result.add(root.val);
            if (null != root.left) {
                helper(root.left, result);
            }
            if (null != root.right) {
                helper(root.right, result);
            }
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list;
    }
    */

    // 方法二：迭代
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        while (stack.size() > 0) {
            TreeNode node = stack.removeFirst();
            result.add(node.val);
            if (null != node.right) {
                stack.addFirst(node.right);
            }
            if (null != node.left) {
                stack.addFirst(node.left);
            }
        }
        return result;
    }
}
