package other;

public class P404 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//                 3
//                / \
//                9  20
//                  /  \
//                 15   7

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        int left = 0, right = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null)
                left = root.left.val;
            else
                left = sumOfLeftLeaves(root.left);
        }
        if (root.right != null)
            right = sumOfLeftLeaves(root.right);
        return left + right;
    }

}
