package other;

import java.util.*;

public class _leetcode_9_2 {

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

    public boolean isEvenOddTree(TreeNode root) {
        List<TreeNode> waitList = new LinkedList<>();
        waitList.add(root);
        boolean isOdd = true;
        List<TreeNode> newList = new LinkedList<>();
        List<TreeNode> tList;
        while (!waitList.isEmpty()) {
            int last = -1;
            for (TreeNode node : waitList) {
                if (isOdd) {
                    if (node.val % 2 == 0)
                        return false;
                    if (last != -1 && node.val <= last)
                        return false;
                    last = node.val;
                } else {
                    if (node.val % 2 != 0)
                        return false;
                    if (last != -1 && node.val >= last)
                        return false;
                    last = node.val;
                }
                if (node.left != null)
                    newList.add(node.left);
                if (node.right != null)
                    newList.add(node.right);
            }
            waitList.clear();
            tList = waitList;
            waitList = newList;
            newList = tList;
            isOdd = !isOdd;
        }
        return true;
    }

}
