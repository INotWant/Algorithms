package other;

public class P114 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void helper(TreeNode node, TreeNode[] lastNode) {
        if (null != lastNode[0]) {
            lastNode[0].left = null;
            lastNode[0].right = node;
        }
        lastNode[0] = node;
        TreeNode rightNode = node.right;
        if (null != node.left) {
            helper(node.left, lastNode);
        }
        if (null != rightNode) {
            helper(rightNode, lastNode);
        }
    }

    public void flatten(TreeNode root) {
        if (null != root) {
            helper(root, new TreeNode[1]);
        }
    }

}
