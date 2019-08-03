package other;

public class P297 {

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
            return "";
        StringBuilder sb = new StringBuilder();
        pre(root, sb);
        sb.delete(sb.length() - 1, sb.length());
        sb.append(";");
        mid(root, sb);
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    private void pre(TreeNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val).append("_").append(root.toString()).append(",");
            pre(root.left, sb);
            pre(root.right, sb);
        }

    }

    private void mid(TreeNode root, StringBuilder sb) {
        if (root != null) {
            mid(root.left, sb);
            sb.append(root.val).append("_").append(root.toString()).append(",");
            mid(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data))
            return null;

        String[] split = data.split(";");
        String preStr = split[0];
        String midStr = split[1];

        String[] preSplit = preStr.split(",");
        String[] midSplit = midStr.split(",");

        String valStr = preSplit[0];
        int val = Integer.parseInt(valStr.substring(0, valStr.indexOf('_')));
        TreeNode root = new TreeNode(val);
        help(preSplit, midSplit, root, 0, 0, preSplit.length - 1, 1);
        return root;
    }

    private void help(String[] pre, String[] mid, TreeNode root, int rootIndex, int sM, int eM, int pP) {
        if (sM < eM) {
            int index = sM;
            for (int i = sM; i <= eM; i++)
                if (mid[i].equals(pre[rootIndex])) {
                    index = i;
                    break;
                }
            if (index == sM) {
                String valStr = pre[pP];
                int var = Integer.parseInt(valStr.substring(0, valStr.indexOf("_")));
                root.right = new TreeNode(var);
                help(pre, mid, root.right, pP, index + 1, eM, pP + 1);
            } else if (index == eM) {
                String valStr = pre[pP];
                int var = Integer.parseInt(valStr.substring(0, valStr.indexOf("_")));
                root.left = new TreeNode(var);
                help(pre, mid, root.left, pP, sM, index - 1, pP + 1);
            } else {
                String valStr = pre[pP];
                int var = Integer.parseInt(valStr.substring(0, valStr.indexOf("_")));
                root.left = new TreeNode(var);
                help(pre, mid, root.left, pP, sM, index - 1, pP + 1);
                valStr = pre[pP + index - sM];
                var = Integer.parseInt(valStr.substring(0, valStr.indexOf("_")));
                root.right = new TreeNode(var);
                help(pre, mid, root.right, pP + index - sM, index + 1, eM, pP + index - sM + 1);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        P297 obj = new P297();
        String serialize = obj.serialize(root);
        System.out.println(serialize);
        TreeNode treeNode = obj.deserialize(serialize);
        System.out.println();

    }

}
