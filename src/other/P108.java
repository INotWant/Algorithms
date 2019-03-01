package other;

public class P108 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void helper(int[] nums, TreeNode rootNode, int startIndex, int endIndex) {
        if (endIndex > startIndex) {
            int midIndex = (startIndex + endIndex) / 2;
            if (midIndex > startIndex) {
                int newMid = (startIndex + midIndex - 1) / 2;
                TreeNode node = new TreeNode(nums[newMid]);
                rootNode.left = node;
                helper(nums, node, startIndex, midIndex - 1);
            }
            if (endIndex > midIndex) {
                int newMid = (endIndex + midIndex + 1) / 2;
                TreeNode node = new TreeNode(nums[newMid]);
                rootNode.right = node;
                helper(nums, node, midIndex + 1, endIndex);
            }
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return null;
        }
        TreeNode rootNode = new TreeNode(nums[(nums.length - 1) / 2]);
        helper(nums, rootNode, 0, nums.length - 1);
        return rootNode;
    }

    public static void main(String[] args) {
        int[] nums = {0};
        P108 p108 = new P108();
        TreeNode rootNode = p108.sortedArrayToBST(nums);
        System.out.println();
    }

}
