package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P107 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int levelOrderHelper(TreeNode root, int level, Map<Integer, List<Integer>> map) {
        int result = level;
        List<Integer> value = map.computeIfAbsent(level, k -> new ArrayList<>());
        value.add(root.val);
        if (null != root.left) {
            int tResult = levelOrderHelper(root.left, level + 1, map);
            if (tResult > result) {
                result = tResult;
            }
        }
        if (null != root.right) {
            int tResult = levelOrderHelper(root.right, level + 1, map);
            if (tResult > result) {
                result = tResult;
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (null == root) {
            return lists;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int maxLevel = levelOrderHelper(root, 1, map);
        for (int i = maxLevel; i >= 1; i--) {
            lists.add(map.get(i));
        }
        return lists;
    }

    public static void main(String[] args) {
    }

}
