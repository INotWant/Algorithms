package other;

public class P99 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 常熟空间的解决方案
    public void recoverTree(TreeNode root) {
        TreeNode[] result = new TreeNode[2];
        helper(root, new Data(), result);
        int temp = result[0].val;
        result[0].val = result[1].val;
        result[1].val = temp;
    }

    private static class Data {
        TreeNode beforeNode = null;
    }

    private void helper(TreeNode node, Data data, TreeNode[] result) {
        if (node != null) {
            if (node.left != null)
                helper(node.left, data, result);
            if (result[0] == null && data.beforeNode != null && data.beforeNode.val > node.val)
                result[0] = data.beforeNode;
            if (data.beforeNode != null && data.beforeNode.val > node.val)
                result[1] = node;
            data.beforeNode = node;
            if (node.right != null)
                helper(node.right, data, result);
        }
    }

}
