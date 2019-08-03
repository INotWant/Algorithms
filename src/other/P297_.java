package other;

public class P297_ {

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
        if (root == null)
            return "#";
        StringBuilder sb = new StringBuilder();
        sb.append(((char) (root.val + '0')));
        String left = serialize(root.left);
        String right = serialize(root.right);
        sb.append(left).append(right);
        return sb.toString();
    }

    private int index = 0;

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (index >= data.length())
            return null;
        if (data.charAt(index++) == '#')
            return null;
        --index;
        TreeNode node = new TreeNode(data.charAt(index++) - '0');
        node.left = deserialize(data);
        node.right = deserialize(data);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        P297_ obj = new P297_();
        String serialize = obj.serialize(root);
        System.out.println(serialize);
        TreeNode treeNode = obj.deserialize(serialize);
        System.out.println();

    }

}
