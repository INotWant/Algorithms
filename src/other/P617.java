package other;

public class P617 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        helper(t1, t2, null, null);
        return t1;
    }

    private void helper(TreeNode t1, TreeNode t2, TreeNode p1, TreeNode p2) {
        if (t1 != null && t2 != null) {
            t1.val += t2.val;
            helper(t1.left, t2.left, t1, t2);
            helper(t1.right, t2.right, t1, t2);
        } else if (t2 != null) {
            if (p2.left == t2)
                p1.left = t2;
            else
                p1.right = t2;
        }
    }

}
