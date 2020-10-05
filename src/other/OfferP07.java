package other;

public class OfferP07 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0)
            return null;
        return help(0, 0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode help(int index, int start, int end, int[] preorder, int[] inorder) {
        if (end < start)
            return null;
        int val = preorder[index];
        TreeNode root = new TreeNode(val);
        if (start == end)
            return root;
        int i = start;
        for (; i <= end; i++)
            if (inorder[i] == val)
                break;
        root.left = help(index + 1, start, i - 1, preorder, inorder);
        root.right = help(index + (i - start) + 1, i + 1, end, preorder, inorder);
        return root;
    }

    public static void main(String[] args) {
        OfferP07 offerP07 = new OfferP07();

//        int[] p = {3, 9, 20, 15, 7};
//        int[] i = {9, 3, 15, 20, 7};

        int[] p = {1, 2};
        int[] i = {2, 1};

        TreeNode root = offerP07.buildTree(p, i);
        System.out.println();
    }

}
