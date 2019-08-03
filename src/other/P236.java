package other;

import java.util.*;

public class P236 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        Map<TreeNode, TreeNode> spMap = new HashMap<>();
        spMap.put(root, null);

        List<TreeNode> list = new ArrayList<>();
        list.add(root);

        int count = 0;
        while (list.size() > 0 && count < 2) {
            List<TreeNode> tList = new ArrayList<>();
            for (TreeNode node : list) {
                TreeNode lNode = node.left;
                TreeNode rNode = node.right;

                if (lNode != null) {
                    spMap.put(lNode, node);
                    tList.add(lNode);
                }
                if (rNode != null) {
                    spMap.put(rNode, node);
                    tList.add(rNode);
                }

                if (lNode == p || lNode == q)
                    ++count;
                if (rNode == p || rNode == q)
                    ++count;
                if (count == 2)
                    break;
            }
            list = tList;
        }

        Stack<TreeNode> sp = new Stack<>();
        Stack<TreeNode> sq = new Stack<>();

        TreeNode node = p;
        while (node != null) {
            sp.push(node);
            node = spMap.get(node);
        }

        node = q;
        while (node != null) {
            sq.push(node);
            node = spMap.get(node);
        }

        TreeNode rNode = null;
        while (!sp.empty() && !sq.empty()) {
            node = sp.pop();
            if (sq.pop() == node)
                rNode = node;
            else
                break;
        }
        return rNode;
    }
    */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }

}
