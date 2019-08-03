package other;

public class P437 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

/*
    private int count = 0;

    public int pathSum(TreeNode root, int sum) {
        helper(root, "", sum);
        return count;
    }

    private void helper(TreeNode root, String path, int sum) {
        if (root == null)
            return;
        if (root.val == sum)
            ++count;

        List<Integer> list = new ArrayList<>();
        String[] split = path.split(",");
        for (String s : split)
            if (!s.equals(""))
                list.add(Integer.parseInt(s));
        int tSum = root.val;
        for (int i = list.size() - 1; i >= 0; i--) {
            tSum += list.get(i);
            if (tSum == sum)
                ++count;
        }

        if (root.left != null)
            helper(root.left, path + "," + root.val, sum);
        if (root.right != null)
            helper(root.right, path + "," + root.val, sum);
    }
    */

    // 解法二
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;

        int res = help(root, sum);
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        return res;
    }

    private int help(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int res = 0;
        if (root.val == sum)
            ++res;
        res += help(root.left, sum - root.val);
        res += help(root.right, sum - root.val);

        return res;
    }

//                 10
//                /  \
//               5   -3
//              / \    \
//             3   2   11
//            / \   \
//           3  -2   1

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(-3);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(11);
        TreeNode n7 = new TreeNode(3);
        TreeNode n8 = new TreeNode(-2);
        TreeNode n9 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;
        n5.right = n9;

        P437 obj = new P437();
        System.out.println(obj.pathSum(n1, 8));

    }

}
