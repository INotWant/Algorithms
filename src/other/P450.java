package other;

public class P450 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null || (root.val == key && root.left == null && root.right == null))
            return null;
        TreeNode node = root;
        TreeNode pNode = null;

        while (node != null) {
            if (node.val == key)
                break;
            if (node.val > key) {
                pNode = node;
                node = node.left;
            } else {
                pNode = node;
                node = node.right;
            }
        }
        if (node == null)
            return root;
        if (node.left == null && node.right == null) {
            if (pNode.left == node)
                pNode.left = null;
            else
                pNode.right = null;
        } else if (node.left != null) {
            TreeNode cNode = node.left;
            if (cNode.right == null) {
                node.val = cNode.val;
                node.left = cNode.left;
            } else {
                pNode = cNode;
                while (cNode.right != null) {
                    pNode = cNode;
                    cNode = cNode.right;
                }
                node.val = cNode.val;
                pNode.right = cNode.left;
            }
        } else {
            TreeNode cNode = node.right;
            if (cNode.left == null) {
                node.val = cNode.val;
                node.right = cNode.right;
            } else {
                pNode = cNode;
                while (cNode.left != null) {
                    pNode = cNode;
                    cNode = cNode.left;
                }
                node.val = cNode.val;
                pNode.left = cNode.right;
            }
        }
        return root;
    }
    */

    // 递归
    public TreeNode deleteNode(TreeNode root, int key) {
        // 1.根节点为空
        if (root == null)
            return null;

        // 2.根节点不为空
        // 2.1若删除的节点是根值
        if (key == root.val) {
            // 该根节点没有子节点
            if (root.left == null && root.right == null)
                return null;
            // 该根节点有左节点没有右节点
            else if (root.left != null && root.right == null)
                root = root.left;
            // 该根节点有右节点但是没有子节点
            else if (root.left == null)
                root = root.right;
            // 既有右节点，又有左节点
            else {
                // 获取右子树的最小节点
                TreeNode temp = root.right;
                while (temp.left != null)
                    temp = temp.left;
                // 删除这个最小节点
                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
        } else if (key < root.val)
            root.left = deleteNode(root.left, key);
        else
            root.right = deleteNode(root.right, key);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(7);

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;


        P450 p450 = new P450();
        p450.deleteNode(root, 3);
        System.out.println();
    }

}
