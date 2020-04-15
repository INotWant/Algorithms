package other;

import java.util.LinkedList;
import java.util.Queue;

public class P449 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            Queue<TreeNode> tQueue = new LinkedList<>();
            for (TreeNode treeNode : queue) {
                if (treeNode == null)
                    sb.append('n').append(',');
                else {
                    sb.append(treeNode.val).append(',');
                    tQueue.add(treeNode.left);
                    tQueue.add(treeNode.right);
                }
            }
            queue = tQueue;
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.charAt(0) == 'n')
            return null;
        String[] split = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        queue.add(root);
        for (int i = 1; i < split.length; i += 2) {
            TreeNode node = queue.remove();
            if (split[i].equals("n"))
                node.left = null;
            else {
                node.left = new TreeNode(Integer.parseInt(split[i]));
                queue.add(node.left);
            }
            if (split[i + 1].equals("n"))
                node.right = null;
            else {
                node.right = new TreeNode(Integer.parseInt(split[i + 1]));
                queue.add(node.right);
            }
        }
        return root;
    }


}
