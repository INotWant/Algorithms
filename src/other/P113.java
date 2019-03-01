package other;

import java.util.ArrayList;
import java.util.List;

public class P113 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void helper(TreeNode node, int sum, String history, List<List<Integer>> saveList) {
        if (null == node.left && null == node.right) {
            if (sum == node.val) {
                List<Integer> list = new ArrayList<>();
                String[] split = history.split(",");
                for (String s : split) {
                    list.add(Integer.parseInt(s));
                }
                saveList.add(list);
            }
        }
        if (null != node.left) {
            int var = node.left.val;
            node.left.val += node.val;
            helper(node.left, sum, history + "," + var, saveList);
        }
        if (null != node.right) {
            int var = node.right.val;
            node.right.val += node.val;
            helper(node.right, sum, history + "," + var, saveList);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> saveList = new ArrayList<>();
        if (null == root) {
            return saveList;
        }
        helper(root, sum, String.valueOf(root.val), saveList);
        return saveList;
    }

    public static void main(String[] args) {

    }

}
