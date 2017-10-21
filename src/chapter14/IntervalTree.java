package chapter14;

import org.junit.Test;

/**
 * 【区间树】
 *
 * @author kissx on 2017/10/21.
 */
public class IntervalTree {

    private Node root = null;
    private Node nullNode = new Node();

    public static class Node {
        private Node lcNode;
        private Node rcNode;
        private Node pNode;
        private boolean isRed;
        private double low;
        private double high;
        private double max = Double.MIN_VALUE;    // 自身及子树包括范围的最大值

        public Node getLcNode() {
            return lcNode;
        }

        public Node getRcNode() {
            return rcNode;
        }

        public Node getpNode() {
            return pNode;
        }

        public double getLow() {
            return low;
        }

        public double getHigh() {
            return high;
        }

        public double getMax() {
            return max;
        }

        public boolean isRed() {
            return isRed;
        }
    }

    public void insert(double low, double high) {
        Node node = new Node();
        node.low = low;
        node.high = high;
        node.max = high;
        node.isRed = true;
        node.lcNode = nullNode;
        node.rcNode = nullNode;
        Node pNode = nullNode;
        Node cNode = root;
        while (cNode != nullNode && cNode != null) {
            pNode = cNode;
            if (low >= cNode.low)
                cNode = cNode.rcNode;
            else
                cNode = cNode.lcNode;
        }
        if (pNode == nullNode) {
            root = node;
            node.pNode = nullNode;
        } else if (low >= pNode.low) {
            pNode.rcNode = node;
            node.pNode = pNode;
        } else {
            pNode.lcNode = node;
            node.pNode = pNode;
        }
        // max 维护
        insertParentalSetting(node);
        insertFixUp(node);
    }

    // 修改插入点父辈们的 max 值
    private void insertParentalSetting(Node node) {
        double max = node.max;
        node = node.pNode;
        while (node != nullNode) {
            if (max > node.max)
                node.max = max;
            else
                break;
            node = node.pNode;
        }
    }

    // 插入修改
    private void insertFixUp(Node z) {
        while (z.pNode.isRed) {
            if (z.pNode.pNode.lcNode == z.pNode) {
                Node y = z.pNode.pNode.rcNode;
                if (y.isRed) {                                              // case 1
                    y.isRed = false;
                    z.pNode.isRed = false;
                    z.pNode.pNode.isRed = true;
                    z = z.pNode.pNode;
                } else if (z.pNode.pNode == z) {                           // case 2
                    z.pNode.rcNode = z.lcNode;
                    z.pNode.rcNode.pNode = z.pNode;
                    y = z.pNode;
                    leftRotate(z.pNode, z);
                    z.lcNode = y;
                    z.lcNode.pNode = z;
                    z = z.lcNode;
                } else {                                                    // case 3
                    z = z.pNode;
                    z.isRed = false;
                    z.pNode.isRed = true;
                    z.pNode.lcNode = z.rcNode;
                    z.pNode.lcNode.pNode = z.pNode;
                    y = z.pNode;
                    rightRotate(z.pNode, z);
                    z.rcNode = y;
                    z.rcNode.pNode = z;
                    break;
                }
            } else {
                Node y = z.pNode.pNode.lcNode;
                if (y.isRed) {                                              // case 1
                    y.isRed = false;
                    z.pNode.isRed = false;
                    z.pNode.pNode.isRed = true;
                    z = z.pNode.pNode;
                } else if (z.pNode.lcNode == z) {                           // case 2
                    z.pNode.lcNode = z.rcNode;
                    z.pNode.lcNode.pNode = z.pNode;
                    y = z.pNode;
                    rightRotate(z.pNode, z);
                    z.rcNode = y;
                    z.rcNode.pNode = z;
                    z = z.rcNode;
                } else {                                                    // case 3
                    z = z.pNode;
                    z.isRed = false;
                    z.pNode.isRed = true;
                    z.pNode.rcNode = z.lcNode;
                    z.pNode.rcNode.pNode = z.pNode;
                    y = z.pNode;
                    leftRotate(z.pNode, z);
                    z.lcNode = y;
                    z.lcNode.pNode = z;
                    break;
                }
            }
        }
        this.root.isRed = false;
    }

    // 左旋（逆时针，以 n2 为轴）
    private void leftRotate(Node n1, Node n2) {
        transplant(n1, n2);
        n1.rcNode = n2.lcNode;
        n1.rcNode.pNode = n1;
        n2.lcNode = n1;
        n2.lcNode.pNode = n2;
        n2.max = n1.max;
        double max = n1.lcNode.max > n1.rcNode.max ? n1.lcNode.max : n1.rcNode.max;
        max = max > n1.high ? max : n1.high;
        n1.max = max;
    }

    // 右旋（顺时针，以 n2 为轴）
    private void rightRotate(Node n1, Node n2) {
        transplant(n1, n2);
        n1.lcNode = n2.rcNode;
        n1.lcNode.pNode = n1;
        n2.rcNode = n1;
        n2.rcNode.pNode = n2;
        n2.max = n1.max;
        double max = n1.lcNode.max > n1.rcNode.max ? n1.lcNode.max : n1.rcNode.max;
        max = max > n1.high ? max : n1.high;
        n1.max = max;
    }

    // 用 v 子树 代替 u 子树（只更改最上层指针结构）
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

    /**
     * 删除节点
     *
     * @param z 待删除的结点
     * @return 删除成功返回已删除的结点，否则返回 NULL
     */
    public Node delete(Node z) {
        if (z != nullNode && z != null) {
            Node x = nullNode;
            Node y = z;
            Node node = z.pNode;
            boolean isORed = y.isRed();
            if (z.lcNode == nullNode && z.rcNode == nullNode) {
                if (z.pNode == nullNode)
                    this.root = nullNode;
                else if (z.pNode.lcNode == z)
                    z.pNode.lcNode = nullNode;
                else
                    z.pNode.rcNode = nullNode;
            } else if (z.lcNode == nullNode) {
                x = z.rcNode;
                transplant(z, z.rcNode);
            } else if (z.rcNode == nullNode) {
                x = z.lcNode;
                transplant(z, z.lcNode);
            } else {
                y = getMin(z.rcNode);
                x = y.rcNode;
                if (y.pNode == z)
                    x.pNode = y;
                isORed = y.isRed();
                if (y.rcNode != nullNode) {
                    transplant(y, y.rcNode);
                } else if (y.pNode != z)
                    y.pNode.lcNode = nullNode;
                Node temp = y.pNode;
                transplant(z, y);
                y.lcNode = z.lcNode;
                y.lcNode.pNode = y;
                if (temp != z) {
                    y.rcNode = z.rcNode;
                    y.rcNode.pNode = y;
                }
                y.isRed = z.isRed;
                // 维护 max
                if (y.rcNode != nullNode) {
                    temp = x.pNode;
                    while (temp != y.pNode) {
                        double max = temp.high;
                        max = max > temp.lcNode.max ? max : temp.lcNode.max;
                        temp.max = max > temp.rcNode.max ? max : temp.rcNode.max;
                        temp = temp.pNode;
                    }
                }
            }
            deleteParentalSetting(node);
            if (!isORed)
                deleteFixUp(x);
        }
        return z;
    }

    // 修改插入点父辈们的 max 值
    private void deleteParentalSetting(Node node) {
        if (node == nullNode)
            return;
        double max = node.max;
        double tMax = node.high;
        tMax = tMax > node.lcNode.max ? tMax : node.lcNode.max;
        tMax = tMax > node.rcNode.max ? tMax : node.rcNode.max;
        if (tMax < max)
            while (node != nullNode) {
                node.max = tMax;
                node = node.pNode;
            }
    }

    // 删除修改
    private void deleteFixUp(Node x) {
        while (x != root && !x.isRed()) {
            if (x == x.pNode.lcNode) {
                Node w = x.pNode.rcNode;
                if (w.isRed()) {                                    // case 1
                    w.isRed = false;
                    x.pNode.isRed = true;
                    leftRotate(w.pNode, w);
                    w = x.pNode.rcNode;
                }
                if (!w.lcNode.isRed() && !w.rcNode.isRed()) {       // case 2
                    w.isRed = true;
                    x = x.pNode;
                } else {
                    if (!w.rcNode.isRed()) {                        // case 3
                        w.lcNode.isRed = false;
                        w.isRed = true;
                        rightRotate(w, w.lcNode);
                        w = x.pNode.rcNode;
                    }                                               // case 4
                    w.isRed = x.pNode.isRed;
                    x.pNode.isRed = false;
                    w.rcNode.isRed = false;
                    leftRotate(w.pNode, w);
                    x = this.root;
                }
            } else {
                Node w = x.pNode.lcNode;
                if (w.isRed()) {
                    w.isRed = false;
                    x.pNode.isRed = true;
                    rightRotate(w.pNode, w);
                    w = x.pNode.lcNode;
                }
                if (!w.rcNode.isRed && !w.lcNode.isRed) {
                    w.isRed = true;
                    x = x.pNode;
                } else {
                    if (!w.lcNode.isRed) {
                        w.rcNode.isRed = false;
                        w.isRed = true;
                        leftRotate(w, w.lcNode);
                        w = x.pNode.lcNode;
                    }
                    w.isRed = x.pNode.isRed;
                    x.pNode.isRed = false;
                    w.lcNode.isRed = false;
                    rightRotate(w.pNode, w);
                    x = this.root;
                }
            }
        }
        x.isRed = false;
    }

    /**
     * @return n 子树的最小结点
     */
    private Node getMin(Node n) {
        Node cNode = n;
        Node pNode = nullNode;
        while (cNode != nullNode) {
            pNode = cNode;
            cNode = cNode.lcNode;
        }
        return pNode;
    }

    /**
     * @return 返回第一个与 [low,high] 重叠的结点，
     */
    public Node search(double low, double high) {
        if (high >= low) {
            Node cNode = root;
            while (cNode != null) {
                if (cNode.low > high || low > cNode.high) {
                    if (cNode.lcNode != nullNode && cNode.lcNode.max >= low) {
                        cNode = cNode.lcNode;
                    } else
                        cNode = cNode.rcNode;
                } else
                    return cNode;
            }
        }
        return nullNode;
    }

//    ------------------------ TEST ------------------------

    @Test
    public void testInsert() {
        IntervalTree it = new IntervalTree();
        it.insert(0, 3);
        it.insert(15, 23);
        it.insert(6, 10);
        it.insert(5, 8);
        it.insert(8, 9);
        it.insert(16, 21);
        it.insert(26, 26);
        it.insert(25, 30);
        it.insert(17, 19);
        it.insert(19, 20);
        System.out.println(it.toString());
    }

    @Test
    public void testDelete() {
        IntervalTree it = new IntervalTree();
        it.insert(0, 3);
        it.insert(15, 23);
        it.insert(6, 10);
//        it.insert(5, 8);
//        it.insert(8, 9);
//        it.insert(16, 21);
        it.insert(26, 26);
//        it.insert(25, 30);
//        it.insert(17, 19);
//        it.insert(19, 20);
        Node node = it.search(26, 26);
        it.delete(node);
        System.out.println(it.toString());
    }

}
