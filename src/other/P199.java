package other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iwant
 * @date 2019-04-26 14:35
 */
public class P199 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (list.size() > 0) {
            List<TreeNode> tList = new ArrayList<>();
            while (list.size() > 0) {
                TreeNode node = list.remove(0);
                if (list.size() == 0)
                    result.add(node.val);
                if (node.left != null)
                    tList.add(node.left);
                if (node.right != null)
                    tList.add(node.right);
            }
            list.addAll(tList);
        }
        return result;
    }

}
