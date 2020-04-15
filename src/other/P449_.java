package other;

public class P449_ {

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
        StringBuffer sb = new StringBuffer();
        helpSerialize(root, sb);
        if (sb.length() > 0)
            sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    private void helpSerialize(TreeNode root, StringBuffer sb) {
        if (root != null) {
            sb.append(root.val).append(",");
            helpSerialize(root.left, sb);
            helpSerialize(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data))
            return null;
        String[] split = data.split(",");
        int[] arr = new int[split.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return helpDeserialize(arr, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    private int index = 0;

    private TreeNode helpDeserialize(int[] arr, int max, int min) {
        if (index >= arr.length || arr[index] > max || arr[index] < min)
            return null;
        TreeNode root = new TreeNode(arr[index++]);
        root.left = helpDeserialize(arr, root.val, min);
        root.right = helpDeserialize(arr, max, root.val);
        return root;
    }

}
