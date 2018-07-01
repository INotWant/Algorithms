package other;

public class P98 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return helper(root, new Data());
    }

    private static class Data {
        TreeNode beforeNode = null;
    }

    private boolean helper(TreeNode node, Data data) {
        if (node != null) {
            if (node.left != null)
                if (!helper(node.left, data))
                    return false;
            if (data.beforeNode != null && data.beforeNode.val >= node.val)
                return false;
            data.beforeNode = node;
            if (node.right != null)
                if (!helper(node.right, data))
                    return false;
            return true;
        }
        return true;
    }

}
