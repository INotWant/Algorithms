package other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P94 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //* // [1]递归方法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result) {
        if (node != null) {
            if (node.left == null) {
                result.add(node.val);
                helper(node.right, result);
            } else {
                helper(node.left, result);
                result.add(node.val);
                helper(node.right, result);
            }
        }
    }
    // */

    /* // [2] 迭代方法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (stack.size() > 0) {
            TreeNode node = stack.get(0);
            if (node.left == null) {
                result.add(node.val);
                stack.poll();
                if (node.right != null)
                    stack.push(node.right);
            } else {
                stack.push(node.left);
                node.left = null;
            }
        }
        return result;
    }
    // */
}
