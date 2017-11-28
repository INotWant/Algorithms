package chapter21;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 不想交集合 -- 链表表示
 * 【注意：对内部类（内部数据结构）的封装】
 *
 * @author kissx on 2017/11/18.
 */
public class DisjointSetByList<T> {

    private Map<T, SetNode<T>> map = new HashMap<>();

    private static class DataNode<T> {
        private T t;
        private DataNode<T> next;

        private DataNode(T t, DataNode<T> next) {
            this.t = t;
            this.next = next;
        }

    }

    private static class SetNode<T> {
        private DataNode<T> head;
        private DataNode<T> tail;
        private int num;

        @SuppressWarnings("unchecked")
        private T[] getSet(Class<T> tClass) {
            T[] tArray = (T[]) Array.newInstance(tClass, num);
            DataNode<T> cNode = head;
            for (int i = 0; i < tArray.length; i++) {
                tArray[i] = cNode.t;
                cNode = cNode.next;
            }
            return tArray;
        }
    }

    /**
     * 添加新元素
     *
     * @param t 元素 t
     */
    public void makeSet(T t) {
        SetNode<T> setNode = new SetNode<>();
        DataNode<T> dataNode = new DataNode<>(t, null);
        setNode.head = dataNode;
        setNode.tail = dataNode;
        setNode.num = 1;
        map.put(t, setNode);
    }

    /**
     * 返回元素 t 所在集合的“代表”
     *
     * @param t 元素 t
     */
    public T findSet(T t) {
        return map.get(t).head.t;
    }

    /**
     * 返回元素所在集合中的元素
     *
     * @param t      元素 t
     * @param tClass T 类信息
     */
    public T[] findSetElement(T t, Class<T> tClass) {
        return map.get(t).getSet(tClass);
    }

    /**
     * 合并 t1、t2 元素所在的集合
     *
     * @param t1 元素 t1
     * @param t2 元素 t2
     */
    public DisjointSetByList<T> union(T t1, T t2) {
        SetNode<T> setNode1 = map.get(t1);
        SetNode<T> setNode2 = map.get(t2);
        if (setNode1 == setNode2)
            return this;
        if (setNode1.num < setNode2.num) {
            SetNode<T> tempNode = setNode1;
            setNode1 = setNode2;
            setNode2 = tempNode;
        }
        setNode1.tail.next = setNode2.head;
        setNode1.tail = setNode2.tail;
        setNode1.num += setNode2.num;
        DataNode<T> cNode = setNode2.head;
        while (cNode != null) {
            map.put(cNode.t, setNode1);
            cNode = cNode.next;
        }
        return this;
    }


//  ------------------------ TEST ------------------------

    @Test
    public void test() {
        System.out.println("---------------- BEGIN ----------------");
        DisjointSetByList<Character> disjointSetByList = new DisjointSetByList<>();
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        for (char aChar : chars) {
            disjointSetByList.makeSet(aChar);
        }
        disjointSetByList.union('a', 'b');
        disjointSetByList.union('c', 'a');
        disjointSetByList.union('i', 'j');
        disjointSetByList.union('i', 'h');
        disjointSetByList.union('a', 'i');
        Character[] cArray = disjointSetByList.findSetElement('a', Character.class);
        System.out.println(Arrays.toString(cArray));
        System.out.println(Arrays.toString(disjointSetByList.findSetElement('i', Character.class)));
        System.out.println("----------------- END -----------------");
    }
}
