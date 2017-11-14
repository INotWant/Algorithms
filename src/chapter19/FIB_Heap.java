package chapter19;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契堆
 *
 * @author kissx on 2017/11/14.
 */
public class FIB_Heap<K extends Comparable<K>, V> {

    private Node<K, V> minRoot = null;
    private int num = 0;

    public static class Node<K extends Comparable<K>, V> {
        private Integer degree = 0;
        private Node<K, V> parent = null;
        private Node<K, V> child = null;
        private Node<K, V> prev = null;
        private Node<K, V> next = null;
        private Boolean mark = false;
        public final K key;
        public final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void insert(K key, V value) {
        Node<K, V> insertNode = new Node<>(key, value);
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

    public Node<K, V> getMinNode() {
        return minRoot;
    }

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
                    // 维护孩子链表
                    cNode.parent = tNode;
                    Integer sDegree = cNode.degree;
                    int count = 0;
                    do {
                        // 根据 key 大小判断哪一个成为子结点
                        if (tNode.key.compareTo(cNode.key) > 0) {
                            Node<K, V> temp = tNode;
                            tNode = cNode;
                            cNode = temp;
                        }
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
                        ++count;
                    } while (cNode != null);
                    nodeMap.put(sDegree + count, tNode);
                    nodeMap.put(sDegree, null);
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

}