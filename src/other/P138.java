package other;

import java.util.HashMap;
import java.util.Map;

public class P138 {

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        Node newHead = new Node(head.val, null, null);
        Node currNode = head.next;
        Node currNewNode = newHead;
        Map<Node, Node> map = new HashMap<>();
        map.put(head, newHead);
        while (currNode != null) {
            Node newNode = new Node(currNode.val, null, null);
            map.put(currNode, newNode);
            currNewNode.next = newNode;
            currNewNode = newNode;
            currNode = currNode.next;
        }
        currNode = head;
        currNewNode = newHead;
        while (currNode != null) {
            currNewNode.random = map.get(currNode.random);
            currNode = currNode.next;
            currNewNode = currNewNode.next;
        }
        return newHead;
    }
}
