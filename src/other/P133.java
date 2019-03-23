package other;

import java.util.*;

public class P133 {

    class Node {
        public int val;
        List<Node> neighbors;

        Node() {
        }

        Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (null == node) {
            return null;
        }
        // 使用广度搜索
        Node firtNewNode = null;
        Map<Node, Node> accessedMap = new HashMap<>();
        LinkedList<Node> waitList = new LinkedList<>();
        waitList.addLast(node);
        int len = waitList.size();
        while (len > 0) {
            for (int i = 0; i < len; i++) {
                Node tNode = waitList.removeFirst();
                if (null == accessedMap.get(tNode)) {
                    Node newNode = new Node(tNode.val, null);
                    if (null == firtNewNode) {
                        firtNewNode = newNode;
                    }
                    accessedMap.put(tNode, newNode);
                }
                if (null != tNode.neighbors) {
                    List<Node> neighbors = new ArrayList<>();
                    for (Node nNode : tNode.neighbors) {
                        Node newNode;
                        if (null == accessedMap.get(nNode)) {
                            newNode = new Node(nNode.val, null);
                            accessedMap.put(nNode, newNode);
                            waitList.addLast(nNode);
                        } else {
                            newNode = accessedMap.get(nNode);
                        }
                        neighbors.add(newNode);
                    }
                    accessedMap.get(tNode).neighbors = neighbors;
                }
            }
            len = waitList.size();
        }
        return firtNewNode;
    }

}
