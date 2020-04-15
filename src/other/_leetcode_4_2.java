package other;

import java.util.LinkedList;

public class _leetcode_4_2 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root.val == x) {
            int l = countTN(root.left);
            int r = countTN(root.right);
            int max = Math.max(l, r);
            return n - max < max;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        TreeNode xNode = null;
        jump:
        while (list.size() > 0) {
            LinkedList<TreeNode> tList = new LinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                TreeNode node = list.get(i);
                if (node.left != null) {
                    tList.add(node.left);
                    if (node.left.val == x) {
                        xNode = node.left;
                        break jump;
                    }
                }
                if (node.right != null) {
                    tList.add(node.right);
                    if (node.right.val == x) {
                        xNode = node.right;
                        break jump;
                    }
                }
            }
            list = tList;
        }
        int l = countTN(xNode.left);
        int r = countTN(xNode.right);
        int p = n - (1 + l + r);
        int max = Math.max(p, Math.max(l, r));
        return n - max < max;
    }

    private int countTN(TreeNode root) {
        if (root == null)
            return 0;
        int l, r;
        l = countTN(root.left);
        r = countTN(root.right);
        return l + r + 1;
    }

}
