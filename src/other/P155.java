package other;

import java.util.LinkedList;

public class P155 {

    class MinStack {

        private class Node {
            int x;
            Node preNode;
            Node nextNode;

            Node(int x) {
                this.x = x;
            }
        }

        private Node minNode;
        private LinkedList<Node> stack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            this.stack = new LinkedList<>();
        }

        public void push(int x) {
            Node node = new Node(x);
            this.stack.addFirst(node);
            if (minNode == null)
                minNode = node;
            else {
                Node currNode = minNode;
                Node lastNode = null;
                while (currNode != null && node.x > currNode.x) {
                    lastNode = currNode;
                    currNode = currNode.nextNode;
                }
                if (lastNode == null) {
                    node.nextNode = minNode;
                    minNode.preNode = node;
                    minNode = node;
                } else {
                    if (currNode == null) {
                        lastNode.nextNode = node;
                        node.preNode = lastNode;
                    } else {
                        node.preNode = currNode.preNode;
                        node.nextNode = currNode;
                        if (currNode.preNode != null)
                            currNode.preNode.nextNode = node;
                        currNode.preNode = node;
                    }
                }
            }
        }

        public void pop() {
            Node node = this.stack.removeFirst();
            if (node == minNode)
                minNode = minNode.nextNode;
            else {
                node.preNode.nextNode = node.nextNode;
                if (node.nextNode != null)
                    node.nextNode.preNode = node.preNode;
            }
        }

        public int top() {
            return this.stack.get(0).x;
        }

        public int getMin() {
            return minNode.x;
        }
    }

}
