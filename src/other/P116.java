package other;

import java.util.LinkedList;

public class P116 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (null == root) {
            return null;
        }
        LinkedList<Node> queueList = new LinkedList<>();
        queueList.addLast(root);
        Node node;
        while (queueList.size() > 0) {
            int num = queueList.size();
            for (int i = 0; i < num; i++) {
                node = queueList.removeFirst();
                if (i != num - 1) {
                    node.next = queueList.get(0);
                }
                if (null != node.left) {
                    queueList.addLast(node.left);
                }
                if (null != node.right) {
                    queueList.addLast(node.right);
                }
            }
        }
        return root;
    }

}
