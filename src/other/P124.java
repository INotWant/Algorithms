package other;

public class P124 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private void helper(TreeNode root, int[] saves) {
        if (null == root.left && null == root.right) {
            if (root.val > saves[0]) {
                saves[0] = root.val;
            }
        } else {
            int leftV = 0;
            int rightV = 0;
            int newV = root.val;
            if (null != root.left) {
                helper(root.left, saves);
                leftV = root.left.val;
                if (leftV + root.val > newV) {
                    newV = leftV + root.val;
                }
            }
            if (null != root.right) {
                helper(root.right, saves);
                rightV = root.right.val;
                if (rightV + root.val > newV) {
                    newV = rightV + root.val;
                }
            }
            int currV = root.val + leftV + rightV;
            if (currV > saves[0]) {
                saves[0] = currV;
            }
            if (newV > saves[0]){
                saves[0] = newV;
            }
            if (root.val > saves[0]) {
                saves[0] = root.val;
            }
            root.val = newV;
        }
    }

    public int maxPathSum(TreeNode root) {
        int[] saves = new int[1];
        saves[0] = Integer.MIN_VALUE;
        helper(root, saves);
        return Math.max(saves[0], root.val);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node4.left = node7;
        node4.right = node8;
        node6.right = node9;

        P124 p124 = new P124();
        System.out.println(p124.maxPathSum(node1));
    }

}
