package other;

public class P109 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

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

    public TreeNode sortedListToBST(ListNode head) {
        if (null == head) {
            return null;
        }
        int size = 0;
        ListNode node = head;
        while (null != node) {
            ++size;
            node = node.next;
        }
        node = head;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = node.val;
            node = node.next;
        }
        TreeNode rootNode = new TreeNode(nums[(nums.length - 1) / 2]);
        helper(nums, rootNode, 0, nums.length - 1);
        return rootNode;
    }

    public static void main(String[] args) {

    }

}
