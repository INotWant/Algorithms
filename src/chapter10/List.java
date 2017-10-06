package chapter10;

import java.util.Arrays;

/**
 * @author kissx on 2017/10/6.
 *         链表（多维数组实现）
 *         <p>
 *         【注】 下标为 free -1 表示 NIL p 下标为 -2 表示 NIL ，且当二维数组的 pos 列为空对象时，保证 keyArray[pos] 为 NIL
 *         ---------------------------------
 *         | next |    |    |    |    |    |
 *         ---------------------------------
 *         | key  |    |    |    |    |    |
 *         ---------------------------------
 *         | prev |    |    |    |    |    |
 *         ---------------------------------
 */
public class List<T> {

    private final int FREE_END = -1;
    private final int P_END = -2;

    private int[] nextArray;
    private T[] keyArray;
    private int[] prevArray;
    private int free = 0;
    private int p = P_END;


    /**
     * 包装一个元素
     */
    class Node {
        int next;
        T key;
        int prev;

        Node(int next, int prev) {
            this.next = next;
            this.prev = prev;
        }

        private Node(){}

        int getNext() {
            return next;
        }

        int getPrev() {
            return prev;
        }

        T getKey() {
            return key;
        }
    }

    /**
     * 构造一个双向链表（其中初始化了 free 用以表示未使用的空间）
     *
     * @param size 初始队列大小
     */
    @SuppressWarnings("unchecked")
    public List(int size) {
        nextArray = new int[size];
        keyArray = (T[]) new Object[size];
        prevArray = new int[size];
        for (int i = 0; i < size; i++) {
            if (i != size - 1)
                nextArray[i] = i + 1;
            else
                nextArray[i] = FREE_END;
        }
    }

    /**
     * 遍历链表查找与 key 相同的元素
     *
     * @param key key 值
     * @return 包装为 Node 的链表元素
     */
    public Node index(T key) {
        Node node = null;
        int currentP = p;
        while (currentP != P_END) {
            T t = keyArray[currentP];
            if (t.equals(key)) {
                node = new Node();
                node.key = t;
                node.next = nextArray[currentP];
                node.prev = prevArray[currentP];
                break;
            } else
                currentP = nextArray[currentP];
        }
        return node;
    }

    /**
     * 前插
     *
     * @param key 新元素的 key 值
     */
    public void insert(T key) throws OutOfSpaceException {
        if (free == FREE_END)
            throw new OutOfSpaceException("空间已满！");
        else {
            int newFree = nextArray[free];
            keyArray[free] = key;
            nextArray[free] = p;
            prevArray[free] = P_END;
            if (p != P_END)
                prevArray[p] = free;
            p = free;
            free = newFree;
        }
    }

    /**
     * 可以尝试构建 node 来删除某元素。node 的构建要求为：node.next 与 node.prev 与 链表一样。
     *
     * @param node 待删除的元素
     * @return 如果删除成功，则返回原元素；否则，返回 NULL。
     */
    public Node delete(Node node) {
        if (node != null && node.next != FREE_END) {
            Integer pos = Arrays.binarySearch(nextArray, node.next);
            if (pos < 0 || node.prev != prevArray[pos] || keyArray[pos] == null)
                return null;
            if (node.prev == P_END)
                p = node.next;
            else
                nextArray[node.prev] = node.next;
            if (node.next != P_END)
                prevArray[node.next] = node.prev;
            node.key = keyArray[pos];
            keyArray[pos] = null;
            nextArray[pos] = free;
            free = pos;
            return node;
        }
        return null;
    }

    /**
     * 根据 key 值删除元素，只会删除第一个与 key 相等的元素。
     *
     * @param key key 值
     * @return 如果删除成功，则返回原元素；否则，返回 NULL。
     */
    public Node delete(T key) {
        return delete(index(key));
    }

    /**
     * @return 返回链表已用大小
     */
    public int size() {
        int num = 0;
        while (p != P_END) {
            ++num;
            p = nextArray[p];
        }
        return num;
    }

    static class OutOfSpaceException extends Exception {
        OutOfSpaceException() {
        }

        OutOfSpaceException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws OutOfSpaceException {
        List<Integer> list = new List<>(5);
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        List.Node node = list.new Node(3,-2);
        List.Node oldNode = list.delete(node);
        System.out.println("占用空间：" + list.size());
    }

}
