package other;

import java.util.LinkedList;

public class P173 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class BSTIterator {

        private LinkedList<TreeNode> stack = new LinkedList<>();

        public BSTIterator(TreeNode root) {
            TreeNode node = root;
            while (node != null) {
                this.stack.addFirst(node);
                node = node.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode node = this.stack.removeFirst();
            TreeNode newNode = node.right;
            while (newNode != null){
                this.stack.addFirst(newNode);
                newNode = newNode.left;
            }
            return node.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return this.stack.size() > 0;
        }
    }

}
