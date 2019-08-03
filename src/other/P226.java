package other;

/**
 * @author iwant
 * @date 19-6-25 19:12
 * @desc
 */
public class P226 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode tNode = root.left;
        root.left = root.right;
        root.right = tNode;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}
