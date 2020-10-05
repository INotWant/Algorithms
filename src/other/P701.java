package other;

public class P701 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        int v = root.val;
        if (v > val) {
            if (root.left == null)
                root.left = new TreeNode(val);
            else
                insertIntoBST(root.left, val);
        } else {
            if (root.right == null)
                root.right = new TreeNode(val);
            else
                insertIntoBST(root.right, val);
        }
        return root;
    }
    // */

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        TreeNode pNode = null;
        TreeNode currNode = root;
        while (currNode != null) {
            pNode = currNode;
            int v = root.val;
            if (v > val)
                currNode = currNode.left;
            else
                currNode = currNode.right;
        }

        if (pNode.val > val)
            pNode.left = new TreeNode(val);
        else
            pNode.right = new TreeNode(val);

        return root;
    }

}
