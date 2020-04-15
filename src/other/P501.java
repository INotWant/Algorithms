package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P501 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int[] findMode(TreeNode root) {
        if (root == null)
            return new int[0];
        Map<Integer, Integer> saveMap = new HashMap<>();
        help(root, saveMap);
        List<Integer> list = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : saveMap.entrySet()) {
            if (entry.getValue() == maxCount)
                list.add(entry.getKey());
        }
        int[] arr = new int[list.size()];
        int i = 0;
        for (Integer integer : list)
            arr[i++] = integer;
        return arr;
    }

    private int maxCount = 0;

    private void help(TreeNode root, Map<Integer, Integer> saveMap) {
        if (root == null)
            return;
        Integer num = saveMap.get(root.val);
        if (num == null)
            num = 0;
        saveMap.put(root.val, num + 1);
        if (num + 1 > maxCount)
            maxCount = num + 1;
        help(root.left, saveMap);
        help(root.right, saveMap);
    }

}
