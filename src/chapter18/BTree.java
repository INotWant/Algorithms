package chapter18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * B 树
 * 要点 1）：返回一个不可修改的集合；
 * 要点 2）：规范小标的使用！
 *
 * @author kissx on 2017/11/6.
 */
public class BTree<K extends Comparable<K>, V> {

    private BNode<K, V> root;
    private Integer degree; // 最小度数

    public BTree(Integer degree) {
        this.degree = degree;
    }

    public static class BNode<K, V> {
        private List<K> keys = new ArrayList<>();   // 关键字集合
        private List<V> values = new ArrayList<>(); // 关键字对应的数据
        private List<BNode<K, V>> children = new ArrayList<>(); // 子孩子集合
        private boolean isLeaf; // 是否为叶节点
        private int n;  // 关键字的个数

        public int getN() {
            return n;
        }

        public List<K> getKeys() {
            // 返回一个不可以修改的 关键字集合
            return Collections.unmodifiableList(keys);
        }

        public List<V> getValues() {
            return Collections.unmodifiableList(values);
        }

        public List<BNode<K, V>> getChildren() {
            return Collections.unmodifiableList(this.children);
        }

        public boolean isLeaf() {
            return isLeaf;
        }
    }

    /**
     * @return 创建一个 空B树
     */
    public BNode<K, V> buildBTree() {
        this.root = new BNode<>();
        this.root.n = 0;
        this.root.isLeaf = true;
        return root;
    }

    /**
     * @param key   key
     * @param value value
     */
    public void insert(K key, V value) {
        this.root = this.root == null ? buildBTree() : this.root;
        if (this.root.n == 2 * degree - 1) {
            BNode<K, V> nRoot = new BNode<>();
            nRoot.n = 0;
            nRoot.isLeaf = false;
            nRoot.children.add(this.root);
            this.root = nRoot;
            split(nRoot, 0);
        }
        insertNoFull(this.root, key, value);
    }

    /**
     * 分裂结点，添加时不会造成结点关键字大于 2*degree-1
     * 【注】：分裂结点使得 B树 从根节点增高
     *
     * @param pNode 待“分裂”结点的父结点；
     * @param c     待“分裂”结点所在父结点的位置；
     */
    private void split(BNode<K, V> pNode, int c) {
        BNode<K, V> node = pNode.children.get(c);
        BNode<K, V> bNode = new BNode<>();
        // 新结点度数
        bNode.n = degree - 1;
        // 新结点关键字以及子孩子
        for (int i = 0; i < node.n; i++)
            if (i >= degree) {
                bNode.keys.add(node.keys.get(i));
                bNode.values.add(node.values.get(i));
                if (!node.isLeaf)
                    bNode.children.add(node.children.get(i));
            }
        if (!node.isLeaf)
            bNode.children.add(node.children.get(node.children.size() - 1));
        // 新结点是否为叶节点
        bNode.isLeaf = node.isLeaf;
        // 父结点修改
        pNode.keys.add(c, node.keys.get(degree - 1));
        pNode.values.add(c, node.values.get(degree - 1));
        pNode.children.add(c + 1, bNode);
        pNode.n += 1;
        // 旧结点修改
        for (int i = node.n - 1; i >= degree - 1; i--) {
            node.keys.remove(i);
            node.values.remove(i);
            if (!node.isLeaf)
                node.children.remove(i + 1);
        }
        node.n = degree - 1;
    }

    // 插入帮助方法，已确定 node 不会满
    // 只在叶节点插入
    private void insertNoFull(BNode<K, V> node, K key, V value) {
        if (node.isLeaf) {
            int i = 0;
            for (; i < node.n; i++)
                if (node.keys.get(i).compareTo(key) >= 0)
                    break;
            node.keys.add(i, key);
            node.values.add(i, value);
            node.n++;
        } else {
            int i = 0;
            for (; i < node.n; i++)
                if (node.keys.get(i).compareTo(key) >= 0)
                    break;
            BNode<K, V> cNode = node.children.get(i);
            if (cNode.n == 2 * degree - 1) {
                split(node, i);
                if (node.keys.get(i).compareTo(key) < 0)
                    cNode = node.children.get(i + 1);
            }
            insertNoFull(cNode, key, value);
        }
    }

    /**
     * @param key 关键字
     * @return 关键字对应的值
     */
    public V search(K key) {
        BNode<K, V> cNode = this.root;
        while (cNode != null) {
            int i = 0;
            for (; i < cNode.n; i++) {
                if (cNode.keys.get(i).compareTo(key) == 0)
                    return cNode.values.get(i);
                else if (cNode.keys.get(i).compareTo(key) > 0)
                    break;
            }
            if (!cNode.isLeaf)
                cNode = cNode.children.get(i);
            else
                cNode = null;
        }
        return null;
    }

    public static void main(String[] args) {
        BTree<Character, String> bTree = new BTree<>(2);
        bTree.insert('M', "Computer");
        bTree.insert('D', "Hello");
        bTree.insert('H', "Java");
        bTree.insert('Q', "C++");
        bTree.insert('X', "C");
        bTree.insert('T', "Compile");
        bTree.insert('B', "Linux");
        bTree.insert('C', "GitHub");
        System.out.println(bTree.search('A'));
        System.out.println("---------- END ----------");

    }

}
