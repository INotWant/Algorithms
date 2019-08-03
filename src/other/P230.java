package other;

/**
 * @author iwant
 * @date 19-7-1 20:59
 * @desc
 */
public class P230 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public int kthSmallest(TreeNode root, int k) {
        int lTreeSize = getTreeSize(root.left);
        if (k == lTreeSize + 1)
            return root.val;
        if (k <= lTreeSize)
            return kthSmallest(root.left, k);
        else
            return kthSmallest(root.right, k - lTreeSize - 1);
    }

    public int getTreeSize(TreeNode root) {
        if (root == null)
            return 0;
        return getTreeSize(root.left) + getTreeSize(root.right) + 1;
    }

}
