package other;

import java.util.ArrayList;
import java.util.List;

public class P257 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        helper(root, "");
        return result;
    }

    private void helper(TreeNode root, String path) {
        if (root != null) {
            if ("".equals(path))
                path = String.valueOf(root.val);
            else
                path += ("->" + root.val);
            if (root.left == null && root.right == null)
                result.add(path);
            if (root.left != null)
                helper(root.left, path);
            if (root.right != null)
                helper(root.right, path);
        }
    }
}
