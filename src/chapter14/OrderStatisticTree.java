package chapter14;


import org.junit.Test;

/**
 * 顺序统计树：
 * 红黑树的一种扩展，用于在 O(lgn) 上做动态顺序统计。
 * 【秩】：一个元素的秩为中序遍历时输出的位置。
 *
 * @author kissx on 2017/10/19.
 */
public class OrderStatisticTree<T extends Comparable<T>> {

    private Node<T> root = null;    // 根节点
    private final Node<T> nullNode = new Node<>(); // NIL节点（哨兵 T.nail ）

    public static class Node<T> {
        Node<T> pNode;  // 父结点
        Node<T> lcNode; // 左孩子
        Node<T> rcNode; // 右孩子
        Integer tNum = 0;    // 该结点所指子树的结点数（扩展之处）
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

        public Integer gettNum() {
            return tNum;
        }
    }

    /**
     * 查找秩为 i 的结点
     *
     * @param i 位置
     * @return 若查找到返回所得结点，否则，返回 nullNode
     */
    public Node<T> select(int i) {
        return selectHelp(i, this.root);
    }

    /**
     * @param node 结点
     * @return 结点对应的秩
     */
    public int rank(Node<T> node) {
        int r = node.lcNode.tNum + 1;
        while (node.pNode != nullNode) {
            if (node == node.pNode.rcNode)
                r += node.pNode.lcNode.tNum + 1;
            node = node.pNode;
        }
        return r;
    }

    private Node<T> selectHelp(int i, Node<T> cNode) {
        if (cNode != nullNode) {
            int r = cNode.lcNode.tNum + 1;
            if (r == i)
                return cNode;
            else if (r < i)
                return selectHelp(i - r, cNode.rcNode);
            else
                return selectHelp(i, cNode.lcNode);
        }
        return cNode;
    }

    /**
     * 操作同于红黑树 {@link chapter13.RedBlackTree} 插入操作，只是加入了对 tNum 的维护。
     *
     * @param value 待插入值
     */
    public void insert(T value) {
        Node<T> pNode = nullNode;
        Node<T> cNode = root;
        while (cNode != nullNode && cNode != null) {
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
        node.tNum = 1;
        if (pNode == nullNode) {
            root = node;
            node.pNode = nullNode;
        } else if (value.compareTo(pNode.value) >= 0) {
            pNode.rcNode = node;
            node.pNode = pNode;
        } else {
            pNode.lcNode = node;
            node.pNode = pNode;
        }
        // tNum 维护
        parentalAdd(node.pNode);
        insertFixUp(node);
    }

    // node 的自己及祖结点 +1
    private void parentalAdd(Node<T> node) {
        while (node != nullNode) {
            node.tNum++;
            node = node.pNode;
        }
    }

    // 插入修改
    private void insertFixUp(Node<T> z) {
        while (z.pNode.isRed) {
            if (z.pNode.pNode.lcNode == z.pNode) {
                Node<T> y = z.pNode.pNode.rcNode;
                if (y.isRed) {                                              // case 1
                    y.isRed = false;
                    z.pNode.isRed = false;
                    z.pNode.pNode.isRed = true;
                    z = z.pNode.pNode;
                } else if (z.pNode.rcNode == z) {                           // case 2
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
                Node<T> y = z.pNode.pNode.lcNode;
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
        // tNum 维护
        n2.tNum = n1.tNum;
        n1.tNum = n1.lcNode.tNum + n1.rcNode.tNum + 1;
    }

    // 右旋（顺时针，以 n2 为轴）
    private void rightRotate(Node n1, Node n2) {
        transplant(n1, n2);
        n1.lcNode = n2.rcNode;
        n1.lcNode.pNode = n1;
        n2.rcNode = n1;
        n2.rcNode.pNode = n2;
        // tNum 维护
        n2.tNum = n1.tNum;
        n1.tNum = n1.lcNode.tNum + n1.rcNode.tNum + 1;
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
     * 删除节点，操作同于 {@link chapter13.RedBlackTree} 中的删除操作，只是加入 tNum 的维护。
     *
     * @param z 待删除的结点
     * @return 删除成功返回已删除的结点，否则返回 NULL
     */
    public Node<T> delete(Node<T> z) {
        if (z != nullNode && z != null) {
            Node<T> x = nullNode;
            Node<T> y = z;
            Node<T> node = z.pNode;
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
                Node<T> temp = y.pNode;
                transplant(z, y);
                y.lcNode = z.lcNode;
                y.lcNode.pNode = y;
                if (temp != z) {
                    y.rcNode = z.rcNode;
                    y.rcNode.pNode = y;
                }
                y.isRed = z.isRed;
                // 维护 tNum
                if (y.rcNode != nullNode) {
                    temp = y.rcNode;
                    while (temp != x) {
                        temp.tNum--;
                        temp = temp.lcNode;
                    }
                }
                y.tNum = y.lcNode.tNum + y.rcNode.tNum + 1;
            }
            parentalSub(node);
            if (!isORed)
                deleteFixUp(x);
        }
        return z;
    }

    // node 自己及祖结点减 1
    private void parentalSub(Node<T> node) {
        while (node != nullNode) {
            node.tNum--;
            node = node.pNode;
        }
    }

    // 删除修改
    private void deleteFixUp(Node<T> x) {
        while (x != root && !x.isRed()) {
            if (x == x.pNode.lcNode) {
                Node<T> w = x.pNode.rcNode;
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
                Node<T> w = x.pNode.lcNode;
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
    private Node<T> getMin(Node<T> n) {
        Node<T> cNode = n;
        Node<T> pNode = nullNode;
        while (cNode != nullNode) {
            pNode = cNode;
            cNode = cNode.lcNode;
        }
        return pNode;
    }

    /**
     * @param value value值
     * @return 返回第一个与 value 相等的结点。若没有查到则返回 NULL。
     */
    public Node<T> search(T value) {
        Node<T> cNode = root;
        while (cNode != null) {
            if (cNode.value.compareTo(value) == 0)
                return cNode;
            cNode = cNode.value.compareTo(value) > 0 ? cNode.lcNode : cNode.rcNode;
        }
        return null;
    }

//    ---------------------- TEST ----------------------

    @Test
    public void testInsert() {
        OrderStatisticTree<Integer> ost = new OrderStatisticTree<>();
        ost.insert(4);
        ost.insert(14);
        ost.insert(5);
        ost.insert(15);
        ost.insert(1);
        ost.insert(7);
        ost.insert(2);
        ost.insert(11);
        ost.insert(8);
        System.out.println("-------------END-------------");
    }

    @Test
    public void testDelete() {
        OrderStatisticTree<Integer> ost = new OrderStatisticTree<>();
        ost.insert(4);
        ost.insert(23);
        ost.insert(21);
        ost.insert(15);
        ost.insert(1);
        ost.insert(5);
        ost.insert(8);
        ost.insert(11);
        ost.insert(14);
        Node<Integer> node = ost.search(8);
        ost.delete(node);
        System.out.println("-------------END-------------");
    }

    @Test
    public void testSelect() {
        OrderStatisticTree<Integer> ost = new OrderStatisticTree<>();
        ost.insert(4);
        ost.insert(14);
        ost.insert(5);
        ost.insert(15);
        ost.insert(1);
        Node<Integer> node = ost.select(3);
        System.out.println(node.value);
    }

    @Test
    public void testRank() {
        OrderStatisticTree<Integer> ost = new OrderStatisticTree<>();
        ost.insert(4);
        ost.insert(14);
        ost.insert(5);
        ost.insert(15);
        ost.insert(1);
        int i = 4;
        Node<Integer> node = ost.search(i);
        System.out.println(i + "::" + ost.rank(node));
    }
}
