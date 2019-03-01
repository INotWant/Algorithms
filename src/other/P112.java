package other;

public class P112 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void helper(TreeNode node, int sum, boolean[] flags) {
        if (!flags[0]) {
            if (null == node.left && null == node.right) {
                flags[0] = node.val == sum;
                return;
            }
            if (null != node.left) {
                node.left.val += node.val;
                helper(node.left, sum, flags);
            }
            if (null != node.right) {
                node.right.val += node.val;
                helper(node.right, sum, flags);
            }
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }
        boolean[] flags = new boolean[1];
        helper(root, sum, flags);
        return flags[0];
    }

    public static void main(String[] args) {


    }

}
