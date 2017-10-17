package chapter12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树（左小右大，无他求）
 *
 * @author kissx on 2017/10/14.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private TNode<T> tHead;

    public static class TNode<T> {
        private TNode<T> p; // 父节点
        private TNode<T> l; // 左孩子
        private T value;
        private TNode<T> r; // 右孩子

        public TNode<T> getP() {
            return p;
        }

        public TNode<T> getL() {
            return l;
        }

        public T getValue() {
            return value;
        }

        public TNode<T> getR() {
            return r;
        }
    }

    public BinarySearchTree() {
    }

    /**
     * 向二叉搜索树中插入新值。【注】：大于的插入到右孩子，小于等于的插入到左孩子。
     *
     * @param value 待插入的新值
     * @return this
     */
    public BinarySearchTree<T> insert(T value) {
        if (tHead == null) {
            tHead = new TNode<>();
            tHead.p = null;
            tHead.l = null;
            tHead.r = null;
            tHead.value = value;
            return this;
        }
        TNode<T> cNode = tHead;
        TNode<T> pNode = null;
        while (cNode != null) {
            pNode = cNode;
            if (value.compareTo(cNode.value) > 0)
                cNode = cNode.r;
            else
                cNode = cNode.l;
        }
        TNode<T> tNode = new TNode<>();
        tNode.value = value;
        tNode.l = null;
        tNode.r = null;
        tNode.p = pNode;
        if (value.compareTo(pNode.value) > 0)
            pNode.r = tNode;
        else
            pNode.l = tNode;
        return this;
    }

    /**
     * 删除某结点（根据其子树的情况决定删除策略）
     *
     * @param tNode 待删除结点
     * @return 删除的结点
     */
    public TNode<T> delete(TNode<T> tNode) {
        if (tNode != null) {
            if (tNode.l == null && tNode.r == null)
                if (tNode.p == null)
                    this.tHead = null;
                else if (tNode.p.l == tNode)
                    tNode.p.l = null;
                else
                    tNode.p.r = null;
            else if (tNode.l == null)
                transplant(tNode, tNode.r);
            else if (tNode.r == null)
                transplant(tNode, tNode.l);
            else {
                TNode<T> ttNode = getMin(tNode.r);
                if (ttNode.r != null) {
                    transplant(ttNode, ttNode.r);
                } else if (ttNode.p != tNode)
                    ttNode.p.l = null;
                TNode<T> temp = ttNode.p;
                transplant(tNode, ttNode);
                ttNode.l = tNode.l;
                ttNode.l.p = ttNode;
                if (temp != tNode) {
                    ttNode.r = tNode.r;
                    ttNode.r.p = ttNode;
                }
            }
        }
        return tNode;
    }

    /**
     * t 替代 f 子树
     *
     * @param f 被替代子树
     * @param t 替代子树
     */
    private void transplant(TNode<T> f, TNode<T> t) {
        if (f.p == null)
            this.tHead = t;
        else if (f == f.p.l)
            f.p.l = t;
        else
            f.p.r = t;
        t.p = f.p;
    }

    /**
     * @return tNode 子树的最小结点
     */
    public TNode<T> getMin(TNode<T> tNode) {
        TNode<T> cNode = tNode;
        TNode<T> pNode = null;
        while (cNode != null) {
            pNode = cNode;
            cNode = cNode.l;
        }
        return pNode;
    }

    /**
     * @return tNode 子树的最大结点
     */
    public TNode<T> getMax(TNode<T> tNode) {
        TNode<T> cNode = tNode;
        TNode<T> pNode = null;
        while (cNode != null) {
            pNode = cNode;
            cNode = cNode.r;
        }
        return pNode;
    }

    /**
     * @return 二叉搜索树的根节点
     */
    public TNode<T> gettHead() {
        return tHead;
    }

    /**
     * @param value value值
     * @return 返回第一个与 value 相等的结点。若没有查到则返回 NULL。
     */
    public TNode<T> search(T value) {
        TNode<T> cNode = tHead;
        while (cNode != null) {
            if (cNode.value.compareTo(value) == 0)
                return cNode;
            cNode = cNode.value.compareTo(value) > 0 ? cNode.l : cNode.r;
        }
        return null;
    }

    /**
     * 获取前驱（中序遍历）
     */
    public TNode<T> precursor(TNode<T> tNode) {
        if (tNode != null) {
            if (tNode.l != null)
                return getMax(tNode.l);
            TNode<T> pNode = tNode.p;
            TNode<T> cNode = tNode;
            while (pNode != null && pNode.l == cNode) {
                cNode = pNode;
                pNode = pNode.p;
            }
            if (pNode != null)
                return pNode;
            return null;
        }
        return null;
    }

    /**
     * 获取后继(中序遍历)
     */
    public TNode<T> successor(TNode<T> tNode) {
        if (tNode != null) {
            if (tNode.r != null)
                return getMin(tNode.r);
            TNode<T> pNode = tNode.p;
            TNode<T> cNode = tNode;
            while (pNode != null && pNode.r == cNode) {
                cNode = pNode;
                pNode = pNode.p;
            }
            if (pNode != null)
                return pNode;
            return null;
        }
        return null;
    }

    /**
     * 中序遍历
     */
    public List<TNode<T>> traversal() {
        List<TNode<T>> list = new ArrayList<>();
        traversalHelp(this.tHead, list);
        return list;
    }

    private void traversalHelp(TNode<T> tNode, List<TNode<T>> list) {
        if (tNode != null) {
            traversalHelp(tNode.l, list);
            list.add(tNode);
            traversalHelp(tNode.r, list);
        }
    }


    // ---------------------- TEST ----------------------

    @Test
    public void testInsert() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5)
                .insert(2)
                .insert(9)
                .insert(18);
        System.out.println("------End------");
    }

    @Test
    public void testDelete() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(2);
//        bst.insert(9);
//        bst.insert(18);
//        TNode<Integer> mNode = bst.getMin(bst.gettHead());
//        bst.delete(mNode);
        bst.delete(bst.gettHead());
        System.out.println("------End------");
    }

    @Test
    public void testPrecursor() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(2);
        bst.insert(16);
        bst.insert(1);
        bst.insert(4);
        bst.insert(11);
        TNode<Integer> tNode = bst.search(1);
        TNode<Integer> precursor = bst.precursor(tNode);
        System.out.println("------End------");
    }

    @Test
    public void testSuccessor() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(2);
        bst.insert(16);
        bst.insert(1);
        bst.insert(4);
        bst.insert(11);
        TNode<Integer> tNode = bst.search(16);
        TNode<Integer> successor = bst.successor(tNode);
        System.out.println("------End------");
    }

    @Test
    public void testTraversal() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(2);
        bst.insert(16);
        bst.insert(1);
        bst.insert(4);
        bst.insert(11);
        List<TNode<Integer>> list = bst.traversal();
        for (TNode<Integer> tNode : list)
            System.out.println(tNode.value);
    }
}
