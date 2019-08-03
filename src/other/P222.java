package other;

/**
 * @author iwant
 * @date 19-6-26 19:43
 * @desc
 */
public class P222 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int leftCount = 0, rightCount = 0;
        if (root.left != null)
            leftCount = countNodes(root.left);
        if (root.right != null)
            rightCount = countNodes(root.right);
        return leftCount + rightCount + 1;
    }

}
