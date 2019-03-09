package other;

public class P129 {

    private int sum = 0;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void helper(TreeNode root, String hisStr) {
        if (null == root.left && null == root.right) {
            sum += (Integer.parseInt(hisStr + root.val));
        } else {
            if (null != root.left) {
                helper(root.left, hisStr + root.val);
            }
            if (null != root.right) {
                helper(root.right, hisStr + root.val);
            }
        }
    }

    public int sumNumbers(TreeNode root) {
        if (null == root) {
            return 0;
        }
        helper(root, "");
        return sum;
    }

}
