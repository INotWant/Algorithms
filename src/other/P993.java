package other;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class P993 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        boolean xIn = false;
        boolean yIn = false;
        Map<Integer, Integer> map = new HashMap<>();
        TreeNode node;
        while (stack.size() > 0) {
            int num = stack.size();
            for (int i = num; i > 0; i--) {
                node = stack.removeFirst();
                xIn = xIn || (x == node.val);
                yIn = yIn || (y == node.val);
                if (null != node.left) {
                    map.put(node.left.val, node.val);
                    stack.addLast(node.left);
                }
                if (null != node.right) {
                    map.put(node.right.val, node.val);
                    stack.addLast(node.right);
                }
            }
            if (xIn && yIn) {
                return !map.get(x).equals(map.get(y));
            } else if (xIn || yIn) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
        P993 p993 = new P993();
        System.out.println(p993.isCousins(node1, 5, 4));
        System.out.println();
    }

}
