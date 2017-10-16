package chapter13;

/**
 * 红黑树:
 * 1,每个节点都有颜色：黑色或红色；
 * 2，根节点颜色为黑色；
 * 3，叶结点颜色为黑色；
 * 4，红色结点的子节点颜色为黑色；
 * 5，从某结点到叶节点的黑色结点数相同；
 *
 * @author kissx on 2017/10/16.
 */
public class RedBlackTree<T extends Comparable<T>> {

    private Node<T> root = null;
    private final Node<T> nullNode = new Node<>();

    public static class Node<T> {
        Node<T> pNode;
        Node<T> lcNode;
        Node<T> rcNode;
        T value;
        boolean isRed; // true 代表红色，false 代表黑色

        public Node getLcNode() {
            return lcNode;
        }

        public Node getRcNode() {
            return rcNode;
        }

        public Node getpNode() {
            return pNode;
        }

        public T getValue() {
            return value;
        }

        public boolean isRed() {
            return isRed;
        }
    }

    public void insert(T value) {
        Node<T> pNode = null;
        Node<T> cNode = root;
        while (cNode != null) {
            pNode = cNode;
            if (value.compareTo(cNode.value) >= 0)
                cNode = cNode.rcNode;
            else
                cNode = cNode.lcNode;
        }
        Node<T> node = new Node<>();
        node.value = value;
        node.isRed = true;
        node.lcNode = nullNode;
        node.rcNode = nullNode;
        if (pNode == null) {
            root = node;
            node.pNode = nullNode;
        } else if (pNode.value.compareTo(value) >= 0) {
            pNode.rcNode = node;
            node.pNode = pNode;
        } else {
            pNode.lcNode = node;
            node.pNode = pNode;
        }

    }

    private void insertFixup(Node<T> z) {

    }

    private void leftRotate(Node n1, Node n2) {
        transplant(n1, n2);
        n1.rcNode = n2.lcNode;
        n1.rcNode.pNode = n1;
        n2.lcNode = n1;
        n2.lcNode.pNode = n2;
    }

    private void rightRotate(Node n1, Node n2) {
        transplant(n1, n2);
        n1.lcNode = n2.rcNode;
        n1.lcNode.pNode = n1;
        n2.rcNode = n1;
        n2.rcNode.pNode = n2;
    }

    @SuppressWarnings("unchecked")
    private void transplant(Node u, Node v) {
        if (this.root == u) {
            this.root = v;
        } else if (u.pNode.lcNode == u) {
            u.pNode.lcNode = v;
        } else {
            u.pNode.rcNode = v;
        }
        v.pNode = u.pNode;
    }

    public void delete(Node<T> node) {

    }

}
