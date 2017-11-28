package chapter21;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于不想交集合的数据结构 -- 森林实现
 * 【启发式策略】
 *      1）按秩合并
 *      2）缩短路径
 *
 * @author kissx on 2017/11/19.
 */
public class DisjointSetByTree<T> {

    private Map<T, DataNode<T>> map = new HashMap<>();

    private static class DataNode<T> {
        private T t;
        private DataNode<T> parent;
        private int mark;        // 秩

    }

    /**
     * 添加新元素
     *
     * @param t 元素 t
     */
    public void makeSet(T t) {
        DataNode<T> dataNode = new DataNode<>();
        dataNode.t = t;
        dataNode.parent = dataNode;
        dataNode.mark = 0;
        map.put(t, dataNode);
    }

    /**
     * 返回元素 t 所在集合的“代表”
     *
     * @param t 元素 t
     */
    public T findSet(T t) {
        return findSet(map.get(t)).t;
    }

    // findSetHelp
    private DataNode<T> findSet(DataNode<T> dataNode) {
        if (dataNode.parent == dataNode)
            return dataNode;
        dataNode.parent = findSet(dataNode.parent);
        return dataNode.parent;
    }

    /**
     * 合并
     *
     * @param t1 元素 t1
     * @param t2 元素 t2
     */
    public DisjointSetByTree<T> union(T t1, T t2) {
        DataNode<T> dataNode1 = findSet(map.get(t1));
        DataNode<T> dataNode2 = findSet(map.get(t2));
        if (dataNode1 == dataNode2)
            return this;
        if (dataNode1.mark == dataNode2.mark) {
            dataNode1.mark++;
        } else if (dataNode1.mark < dataNode2.mark) {
            DataNode<T> tempDataNode = dataNode1;
            dataNode1 = dataNode2;
            dataNode2 = tempDataNode;
        }
        DataNode<T> th1 = findSet(dataNode1);
        DataNode<T> th2 = findSet(dataNode2);
        th2.parent = th1;
        return this;
    }


//    --------------- TEST ---------------

    @Test
    public void test() {
        System.out.println("---------------- BEGIN ----------------");
        DisjointSetByTree<Character> disjointSetByTree = new DisjointSetByTree<>();
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        for (char c : chars) {
            disjointSetByTree.makeSet(c);
        }
        disjointSetByTree.union('a', 'b');
        disjointSetByTree.union('b', 'c');
        disjointSetByTree.union('d', 'h');
        disjointSetByTree.union('i', 'h');
        disjointSetByTree.union('a', 'i');
        System.out.println("h :: " + disjointSetByTree.findSet('h'));
        System.out.println("----------------- END -----------------");
    }

}
