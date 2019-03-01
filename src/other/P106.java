package other;

public class P106 {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void buildTreeHelp(TreeNode rootNode, int startIndex, int endIndex, int rootPostorderIndex, int[] inorder, int[] postorder) {
        int rootInorderIndex = startIndex;
        for (int i = startIndex; i <= endIndex; i++) {
            if (inorder[i] == postorder[rootPostorderIndex]) {
                rootInorderIndex = i;
                break;
            }
        }
        int beforCount = rootInorderIndex - startIndex;
        int afterCount = endIndex - rootInorderIndex;
        if (beforCount > 0) {
            int childPostorderIndex = rootPostorderIndex - afterCount - 1;
            TreeNode node = new TreeNode(postorder[childPostorderIndex]);
            rootNode.left = node;
            buildTreeHelp(node, startIndex, rootInorderIndex - 1, childPostorderIndex, inorder, postorder);
        }
        if (afterCount > 0) {
            int childPostorderIndex = rootPostorderIndex - 1;
            TreeNode node = new TreeNode(postorder[childPostorderIndex]);
            rootNode.right = node;
            buildTreeHelp(node, rootInorderIndex + 1, endIndex, childPostorderIndex, inorder, postorder);
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (null == inorder || null == postorder) {
            return null;
        }
        if (0 == inorder.length || 0 == postorder.length) {
            return null;
        }
        TreeNode rootNode = new TreeNode(postorder[postorder.length - 1]);
        buildTreeHelp(rootNode, 0, inorder.length - 1, postorder.length - 1, inorder, postorder);
        return rootNode;
    }

    public static void main(String[] args) {
        P106 p106 = new P106();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode rootNode = p106.buildTree(inorder, postorder);
        System.out.println();
    }

}
