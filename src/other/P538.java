package other;

import java.util.LinkedList;

public class P538 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    private static int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return null;
        helper(root);
        return root;
    }

    private void helper(TreeNode root) {
        if (root.right != null)
            helper(root.right);
        int tmp = root.val;
        root.val += sum;
        sum += tmp;
        if (root.left != null)
            helper(root.left);
    }
    */

    private static int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        LinkedList<TreeNode> list = new LinkedList<>();
        TreeNode node = root;
        while (node != null || list.size() > 0) {
            if (node != null) {
                list.addFirst(node);
                node = node.right;
            } else {
                node = list.removeFirst();
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
        }
        return root;
    }

}
