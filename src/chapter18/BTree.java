package chapter18;

import java.util.Collections;
import java.util.LinkedList;
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
        private List<K> keys = new LinkedList<>();   // 关键字集合
        private List<V> values = new LinkedList<>(); // 关键字对应的数据
        private List<BNode<K, V>> children = new LinkedList<>(); // 子孩子集合
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

    /**
     * @param key 待删除的关键字
     * @return 删除成功时，返回关键字对应的“数据”，否则返回 null
     */
    public V delete(K key) {
        return deleteHelp(this.root, key);
    }

    // 删除帮组类
    private V deleteHelp(BNode<K, V> cNode, K key) {
        while (cNode != null) {
            int i = 0;
            for (; i < cNode.n; i++) {
                if (cNode.isLeaf && cNode.keys.get(i).compareTo(key) == 0) {
                    // 要删除的关键字在叶节点的情况
                    --cNode.n;
                    cNode.keys.remove(i);
                    return cNode.values.remove(i);
                } else if (cNode.keys.get(i).compareTo(key) == 0) {
                    // 要删除的关键字在内部结点的情况
                    int b1 = i;
                    int b2 = i + 1;
                    if (cNode.children.get(b1).n >= degree) {
                        // 左兄弟结点关键字数大于等于 t 的情况
                        BNode<K, V> bNode = cNode.children.get(b1);
                        K rKey = bNode.keys.get(bNode.n - 1);
                        V rValue = bNode.values.get(bNode.n - 1);
                        deleteHelp(bNode, rKey);
                        V resultValue = cNode.values.get(i);
                        cNode.keys.set(i, rKey);
                        cNode.values.set(i, rValue);
                        return resultValue;
                    } else if (cNode.children.get(b2).n >= degree) {
                        // 右兄弟结点关键字数大于等于 t 的情况
                        BNode<K, V> bNode = cNode.children.get(b2);
                        K rKey = bNode.keys.get(0);
                        V rValue = bNode.values.get(0);
                        deleteHelp(bNode, rKey);
                        V resultValue = cNode.values.get(i);
                        cNode.keys.set(i, rKey);
                        cNode.values.set(i, rValue);
                        return resultValue;
                    } else {
                        // 相邻结点都不大于的情况，需要合并
                        if (cNode.n - 1 == 0)
                            this.root = cNode.children.get(0);
                        union(cNode, i);
                        return deleteHelp(cNode, key);
                    }
                } else if (cNode.keys.get(i).compareTo(key) > 0)
                    break;
            }
            if (cNode.isLeaf)
                return null;
            else {
                // 要下降，此时要判断下降至结点的关键字的个数
                if (cNode.children.get(i).n >= degree)
                    cNode = cNode.children.get(i);
                else {
                    // 所至结点关键字数 t-1 的情况
                    int b1 = i - 1;
                    int b2 = i + 1;
                    if (b1 >= 0 && cNode.children.get(b1).n >= degree) {
                        // 由其左兄弟输送
                        BNode<K, V> bNode = cNode.children.get(b1);
                        K rKey = bNode.keys.remove(bNode.n - 1);
                        V rValue = bNode.values.remove(bNode.n - 1);
                        BNode<K, V> rChild = null;
                        if (!bNode.isLeaf)
                            rChild = bNode.children.remove(bNode.n);
                        --bNode.n;
                        K moveKey = cNode.keys.get(i - 1);
                        V moveValue = cNode.values.get(i - 1);
                        cNode.keys.set(i - 1, rKey);
                        cNode.values.set(i - 1, rValue);
                        cNode = cNode.children.get(i);
                        ((LinkedList<K>) cNode.keys).addFirst(moveKey);
                        ((LinkedList<V>) cNode.values).addFirst(moveValue);
                        if (!bNode.isLeaf)
                            ((LinkedList<BNode<K, V>>) cNode.children).addFirst(rChild);
                        ++cNode.n;
                    } else if (b2 < cNode.children.size() && cNode.children.get(b2).n >= degree) {
                        // 由其右兄弟输送
                        BNode<K, V> bNode = cNode.children.get(b2);
                        K rKey = bNode.keys.remove(0);
                        V rValue = bNode.values.remove(0);
                        BNode<K, V> rChild = null;
                        if (!bNode.isLeaf)
                            rChild = bNode.children.remove(0);
                        --bNode.n;
                        K moveKey = cNode.keys.get(i);
                        V moveValue = cNode.values.get(i);
                        cNode.keys.set(i, rKey);
                        cNode.values.set(i, rValue);
                        cNode = cNode.children.get(i);
                        ((LinkedList<K>) cNode.keys).addLast(moveKey);
                        ((LinkedList<V>) cNode.values).addLast(moveValue);
                        if (!bNode.isLeaf)
                            ((LinkedList<BNode<K, V>>) cNode.children).addLast(rChild);
                        ++cNode.n;
                    } else { int b = b1 >= 0 ? b1 : b2;
                        if (b == b1)
                            union(cNode, i - 1);
                        else
                            union(cNode, i);
                    }
                }
            }
        }
        return null;
    }

    // 合并 cNode 结点中 i 和 i+1 子结点至 i 结点上
    private void union(BNode<K, V> cNode, int i) {
        K moveKey = cNode.keys.remove(i);
        V moveValue = cNode.values.remove(i);
        BNode<K, V> rChild = cNode.children.remove(i + 1);
        --cNode.n;
        cNode = cNode.children.get(i);
        cNode.keys.add(moveKey);
        cNode.keys.addAll(rChild.getKeys());
        cNode.values.add(moveValue);
        cNode.values.addAll(rChild.getValues());
        if (!rChild.isLeaf)
            cNode.children.addAll(rChild.getChildren());
        cNode.n += 1 + rChild.n;
    }

    public static void main(String[] args) {
        System.out.println("--------- BEGIN ---------");
        BTree<Character, String> bTree = new BTree<>(2);
        bTree.insert('M', "Computer");
        bTree.insert('D', "Hello");
        bTree.insert('H', "Java");
        bTree.insert('Q', "C++");
        bTree.insert('C', "C");
        bTree.insert('I', "SSH");
        bTree.insert('A', "Compile");
        bTree.insert('B', "Linux");
        bTree.insert('T', "GitHub");
        bTree.insert('E', "World");
        bTree.insert('F', "Python");
        bTree.insert('J', "OA");
        bTree.insert('K', "CRM");
        System.out.println(bTree.search('A'));
        System.out.println(bTree.delete('Z'));
        System.out.println("---------- END ----------");
    }

}
