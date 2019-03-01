package other;

public class P110 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int helper(TreeNode node, boolean[] flag) {
        if (flag[0]) {
            if (null == node.left && null == node.right) {
                return 1;
            }
            int leftHigh = 0;
            int rightHigh = 0;
            if (null != node.left) {
                leftHigh = helper(node.left, flag);
            }
            if (null != node.right) {
                rightHigh = helper(node.right, flag);
            }
            if (leftHigh - rightHigh >= 2 || rightHigh - leftHigh >= 2) {
                flag[0] = false;
            }
            if (leftHigh == 0) {
                ++leftHigh;
            }
            if (rightHigh == 0) {
                ++rightHigh;
            }
            return (leftHigh > rightHigh ? leftHigh : rightHigh) + 1;
        }
        return -1;
    }

    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        boolean[] flag = new boolean[1];
        flag[0] = true;
        helper(root, flag);
        return flag[0];
    }

    public static void main(String[] args) {

    }

}
