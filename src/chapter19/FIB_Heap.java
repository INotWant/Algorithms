package chapter19;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契堆
 * <p>
 * 【1】一开始考虑不全，然后添加所缺操作将难上加难！！！（BUG 所在地）
 *
 * @author kissx on 2017/11/14.
 */
public class FIB_Heap<K extends Comparable<K>, V> {

    private Node<K, V> minRoot = null;
    private int num;

    // 结点结构，考虑其对外发布！！！
    public static class Node<K extends Comparable<K>, V> {
        private Integer degree = 0;
        private Node<K, V> parent = null;
        private Node<K, V> child = null;
        private Node<K, V> prev = null;
        private Node<K, V> next = null;
        private Boolean mark = false;
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Integer getDegree() {
            return degree;
        }

        public Node<K, V> getParent() {
            return parent;
        }

        public Node<K, V> getChild() {
            return child;
        }

        public Node<K, V> getPrev() {
            return prev;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public Boolean getMark() {
            return mark;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * 插入操作操作
     *
     * @param key   key 值
     * @param value value 值
     */
    public void insert(K key, V value) {
        insert(new Node<>(key, value));
    }

    /**
     * 插入操作
     *
     * @param insertNode 待插入结点
     */
    public void insert(Node<K, V> insertNode) {
        if (minRoot == null) {
            minRoot = insertNode;
            // 维护双向循环链表
            minRoot.prev = minRoot;
            minRoot.next = minRoot;
        } else {
            // 维护双向循环链表
            insertNode.prev = minRoot.prev;
            insertNode.next = minRoot;
            minRoot.prev.next = insertNode;
            minRoot.prev = insertNode;
            if (insertNode.key.compareTo(minRoot.key) < 0)
                minRoot = insertNode;
        }
        ++num;
    }

    /**
     * 返回最小结点
     */
    public Node<K, V> getMinNode() {
        return minRoot;
    }

    /**
     * 合并两个斐波那契堆，合并后保存在调用此方法的 {@link FIB_Heap} 对象中。
     * 【注：对参数中 fib_heap 的修改仍然会影响合并的新斐波那契堆】
     *
     * @param fib_heap 待合并的斐波那契堆
     * @return this
     */
    public FIB_Heap<K, V> union(FIB_Heap<K, V> fib_heap) {
        // 当 fib_heap 中不为空时，合并才有意义
        if (fib_heap.minRoot != null) {
            if (this.minRoot == null) {
                // 注：对 fib_heap 的更改会影响合并后的 fib_heap
                this.minRoot = fib_heap.minRoot;
            } else {
                // this minRoot结点 的前结点指向 fib_heap minRoot结点
                this.minRoot.prev.next = fib_heap.minRoot;
                // fib_heap minRoot结点 的前结点指向 this minRoot结点
                fib_heap.minRoot.prev.next = this.minRoot;
                Node<K, V> tmPrev = this.minRoot.prev;
                // this minRoot.prev 指向 fib_heap 的前结点
                this.minRoot.prev = fib_heap.minRoot.prev;
                fib_heap.minRoot.prev = tmPrev;
                if (this.minRoot.key.compareTo(fib_heap.minRoot.key) > 0)
                    this.minRoot = fib_heap.minRoot;
            }
        }
        this.num += fib_heap.getNum();
        return this;
    }

    public Node<K, V> getMinRoot() {
        return minRoot;
    }

    public int getNum() {
        return num;
    }

    /**
     * 抽取最小结点
     * 【注：抽取后的合并操作，最为复杂】
     */
    public Node<K, V> extractMin() {
        // 1，获取最小结点，然后去除
        Node<K, V> minNode = this.minRoot;
        if (num == 0 || --num == 0)
            // 无需第二步操作
            this.minRoot = null;
        else {
            // 把删除结点的孩子加入根链表
            Node<K, V> mChild = this.minRoot.child;
            if (mChild != null) {
                do {
                    mChild.parent = null;
                    mChild = mChild.next;
                } while (mChild != this.minRoot.child);
                this.minRoot.prev.next = this.minRoot.child;
                this.minRoot.child.prev.next = this.minRoot;
                Node<K, V> tmPrev = this.minRoot.prev;
                this.minRoot.prev = this.minRoot.child.prev;
                this.minRoot.child.prev = tmPrev;
            }
            // 去除最小结点
            this.minRoot.next.prev = this.minRoot.prev;
            this.minRoot.prev.next = this.minRoot.next;
            // 更新 minRoot，并不能保证为最小结点
            this.minRoot = this.minRoot.next;
            // 2，额外操作
            Node<K, V> label = this.minRoot;
            Node<K, V> cNode = label;
            Map<Integer, Node<K, V>> nodeMap = new HashMap<>();
            do {
                Node<K, V> nNode = cNode.next;
                Node<K, V> tNode = nodeMap.get(cNode.degree);
                if (tNode == null)
                    nodeMap.put(cNode.degree, cNode);
                else {
                    nodeMap.put(cNode.degree, null);
                    Integer sDegree = cNode.degree;
                    int count = 0;
                    do {
                        // 根据 key 大小判断哪一个成为子结点
                        if (tNode.key.compareTo(cNode.key) > 0) {
                            Node<K, V> temp = tNode;
                            tNode = cNode;
                            cNode = temp;
                        }
                        // 维护孩子链表
                        cNode.parent = tNode;
                        if (tNode.child == null) {
                            // 孩子链表为空时
                            tNode.child = cNode;
                            cNode.prev = cNode;
                            cNode.next = cNode;
                        } else {
                            // 孩子链表不为空时
                            cNode.prev = tNode.child.prev;
                            cNode.next = tNode.child;
                            tNode.child.prev.next = cNode;
                            tNode.child.prev = cNode;
                        }
                        ++tNode.degree;
                        // 需要考虑 put 前是否为空的情况
                        cNode = nodeMap.get(tNode.degree);
                        nodeMap.put(tNode.degree, null);
                        ++count;
                    } while (cNode != null);
                    nodeMap.put(sDegree + count, tNode);
                }
                cNode = nNode;
            } while (cNode != label);
            short i = 0;
            for (Map.Entry<Integer, Node<K, V>> entry : nodeMap.entrySet()) {
                Node<K, V> node = entry.getValue();
                if (node != null) {
                    if (i++ == 0) {
                        node.prev = node;
                        node.next = node;
                        this.minRoot = node;
                    } else {
                        node.next = this.minRoot;
                        node.prev = this.minRoot.prev;
                        this.minRoot.prev.next = node;
                        this.minRoot.prev = node;
                        if (node.key.compareTo(this.minRoot.key) < 0)
                            this.minRoot = node;
                    }
                }
            }
        }
        return minNode;
    }

    /**
     * 修改指定结点的 key 值，要求 key 值只能减少
     *
     * @param node 待修改结点
     * @param key  新 key 值
     */
    public void decreaseKey(Node<K, V> node, K key) {
        if (key.compareTo(node.key) > 0)
            throw new RuntimeException("key 比原 key 大！");
        node.key = key;
        Node<K, V> pNode = node.parent;
        if (pNode != null) {
            if (pNode.key.compareTo(node.key) > 0) {
                cut(node, pNode);
                cascadingCut(pNode);
            }
        }
        if (this.minRoot.key.compareTo(node.key) > 0)
            this.minRoot = node;
    }

    // 把 node cut 到根链表中
    private void cut(Node<K, V> node, Node<K, V> pNode) {
        if (--pNode.degree == 0) {
            pNode.child = null;
        } else {
            pNode.child = node.next;
            // 去除 node
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        // 将 node 插入到 minRoot 前面
        node.prev = this.minRoot.prev;
        node.next = this.minRoot;
        this.minRoot.prev.next = node;
        this.minRoot.prev = node;
        node.parent = null;
        node.mark = false;
    }

    // 级联 cut 【注：当某结点（除根链表上的结点）的孩子中有大于等于 2 被 cut 时，该结点也被 cut】
    private void cascadingCut(Node<K, V> node) {
        Node<K, V> pNode = node.parent;
        if (pNode != null) {
            if (!node.mark)
                node.mark = true;
            else {
                cut(node, pNode);
                cascadingCut(pNode);
            }
        }
    }

    /**
     * 删除指定结点
     *
     * @param node   待删除结点
     * @param minKey 提供一个小于任何存在结点 key 的 minKey
     */
    public void delete(Node<K, V> node, K minKey) {
        decreaseKey(node, minKey);
        extractMin();
    }

    // ---------------- TEST ----------------

    @Test
    public void testInsert() {
        // 测试双向链表是否正确
        System.out.println("--------- BEGIN ---------");
        FIB_Heap<Integer, String> fib_heap = new FIB_Heap<>();
        fib_heap.insert(6, "efg");
        fib_heap.insert(3, "abc");
        fib_heap.insert(8, "hij");
        fib_heap.insert(9, "ceo");
        System.out.println("---------- END ----------");
    }

    @Test
    public void testUnion() {
        // 测试合并
        System.out.println("--------- BEGIN ---------");
        FIB_Heap<Integer, String> fib_heap_1 = new FIB_Heap<>();
        FIB_Heap<Integer, String> fib_heap_2 = new FIB_Heap<>();
//        fib_heap_1.insert(3,"abc");
//        fib_heap_1.insert(6,"efg");
        fib_heap_2.insert(8, "hij");
//        fib_heap_2.insert(9,"ceo");
        fib_heap_1.union(fib_heap_2);
        System.out.println("---------- END ----------");
    }

    @Test
    public void testExtractMin() {
        System.out.println("--------- BEGIN ---------");
        FIB_Heap<Integer, String> fib_heap = new FIB_Heap<>();
        fib_heap.insert(6, "efg");
        fib_heap.insert(3, "abc");
        fib_heap.insert(8, "hij");
        fib_heap.insert(9, "ceo");
        fib_heap.insert(2, "cfo");
        Node<Integer, String> minNode1 = fib_heap.extractMin();
        Node<Integer, String> minNode2 = fib_heap.extractMin();
        System.out.println("minNode :: " + minNode1.key + " : " + minNode1.value);
        System.out.println("minNode :: " + minNode2.key + " : " + minNode2.value);
        System.out.println("---------- END ----------");
    }

    @Test
    public void testDecreaseKey() {
        System.out.println("--------- BEGIN ---------");
        FIB_Heap<Integer, String> fib_heap = new FIB_Heap<>();
        Node<Integer, String> node1 = new Node<>(13, "efg");
        Node<Integer, String> node2 = new Node<>(12, "abc");
        fib_heap.insert(node1);
        fib_heap.insert(node2);
        fib_heap.insert(11, "hij");
        fib_heap.insert(18, "hij");
        fib_heap.insert(16, "hij");
        fib_heap.insert(15, "hij");
        fib_heap.insert(14, "hij");
        fib_heap.insert(8, "cfo");
        Node<Integer, String> node = new Node<>(9, "ceo");
        fib_heap.insert(node);
        Node<Integer, String> minNode = fib_heap.extractMin();
        System.out.println("minNode :: " + minNode.key + " : " + minNode.value);
        fib_heap.decreaseKey(node1, 1);
        fib_heap.decreaseKey(node2, 6);
        System.out.println("---------- END ----------");
    }


}