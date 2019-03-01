package other;

import java.util.LinkedList;

public class P111 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int depth = 0;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        TreeNode node;
        while (stack.size() > 0) {
            int num = stack.size();
            ++depth;
            for (int i = num; i > 0; i--) {
                node = stack.removeFirst();
                if (null != node.left) {
                    stack.addLast(node.left);
                }
                if (null != node.right) {
                    stack.addLast(node.right);
                }
                if (null == node.left && null == node.right) {
                    return depth;
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {

    }

}
