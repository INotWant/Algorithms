package other;

public class P235 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 假设所有元素都不相等
        if (root == null || p == null || q == null)
            return null;
        if (p == q)
            return p;

        if (p.val > q.val) {
            TreeNode tNode = p;
            p = q;
            q = tNode;
        }

        TreeNode lNode = root.left;
        TreeNode rNode = root.right;
        if (p.val == root.val || q.val == root.val)
            return root;
        if (lNode == null)
            return lowestCommonAncestor(rNode, p, q);
        if (rNode == null)
            return lowestCommonAncestor(lNode, p, q);
        if (p.val < root.val && q.val > root.val)
            return root;
        if (q.val < root.val)
            return lowestCommonAncestor(lNode, p, q);
        else
            return lowestCommonAncestor(rNode, p, q);
    }
    */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);

        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);

        return root;
    }


}
