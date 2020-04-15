package other;

import java.util.ArrayList;
import java.util.List;

public class _leetcode_6_2 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxLevelSum(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        list.add(root);

        int max = Integer.MIN_VALUE;
        int pos = 0;
        int curr = 0;
        while (list.size() > 0) {
            ++curr;
            List<TreeNode> tList = new ArrayList<>();
            int sum = 0;
            for (TreeNode treeNode : list) {
                if (treeNode.left != null)
                    tList.add(treeNode.left);
                if (treeNode.right != null)
                    tList.add(treeNode.right);
                sum += treeNode.val;
            }
            if (max < sum) {
                max = sum;
                pos = curr;
            }
            list = tList;
        }
        return pos;
    }

}
