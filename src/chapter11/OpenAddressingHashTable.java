package chapter11;

/**
 * 开放寻址法实现散列表（哈希函数使用 “双重散列法”）
 * 实现缺陷：时间复杂度 与 装载因子 有很大关系，而实现中并未考虑装载因子
 * 解决方案：
 *     1）根据装载因子，随存储的增加动态扩展散列表的大小
 *
 * @author kissx on 2017/10/11.
 */
public class OpenAddressingHashTable<K, V> {

    private int m;
    private Node[] nodes;

    /**
     * 构建散列表，需初始化槽的大小 【注：此处所去的槽的大小为大于给定 m 的第一个素数，方便构造哈希函数】
     *
     * @param m 槽的大小
     */
    public OpenAddressingHashTable(int m) {
        this.m = getMorePrime(m);
        this.nodes = new Node[this.m];
    }

    static class Node<K, V> {
        K key;
        V value;
    }

    /**
     * 获取比 num 大的第一个素数
     *
     * @param num num
     */
    private int getMorePrime(int num) {
        int i = num;
        for (; ; i++) {
            boolean flag = true;
            for (int j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return i;
        }
    }

    /**
     * 向散列表中添加数据。由于返回为调用对象，故可以连续 put() 调用。
     *
     * @param key   key
     * @param value value
     * @return OpenAddressingHashTable
     */
    public OpenAddressingHashTable<K, V> put(K key, V value) {
        int i = 0;
        int pos = hashFunction(key, i);
        while (nodes[pos] != null) {
            if (i >= 2 * m)
                throw new RuntimeException("hash table overflow!!1");
            if (nodes[pos].key.equals(key)) {
                nodes[pos].value = value;
                return this;
            }
            pos = hashFunction(key, ++i);
        }
        Node<K, V> node = new Node<>();
        node.key = key;
        node.value = value;
        nodes[pos] = node;
        return this;
    }

    /**
     * 根据 key 值获取 value，返回封装的 Node 。若返回为 null，则表明为查找到。
     */
    @SuppressWarnings("unchecked")
    public Node<K, V> search(K key) {
        int i = 0;
        int pos = hashFunction(key, i);
        while (nodes[pos] != null && i < 2 * m) {
            if (nodes[pos].key.equals(key))
                return nodes[pos];
            pos = hashFunction(key, ++i);
        }
        return null;
    }

    /**
     * 根据 key 值删除，返回删除对象。如果返回 null ，表明删除失败。
     */
    @SuppressWarnings("unchecked")
    public Node<K, V> delete(K key) {
        int i = 0;
        int pos = hashFunction(key, i);
        while (nodes[pos] != null && i < 2 * m) {
            if (nodes[pos].key.equals(key)) {
                Node node = nodes[pos];
                nodes[pos] = null;
                return node;
            }
            pos = hashFunction(key, ++i);
        }
        return null;
    }

    /**
     * 使用 双重散列 实现哈希函数
     *
     * @param key key 值
     * @param i   遇到冲突次数
     * @return 由 key 经哈希函数给出的位置
     */
    private int hashFunction(K key, int i) {
        int keyInteger;
        if (key instanceof String)
            keyInteger = str2num((String) key);
        else if (key instanceof Number)
            keyInteger = ((Number) key).intValue();
        else
            keyInteger = key.hashCode();
        int h1 = keyInteger % m;
        int h2 = i * (1 + keyInteger % (m - 1));
        return (h1 + h2) % m;
    }

    /**
     * 将 {@link String} 类型的 key 转为自然数
     */
    private int str2num(String str) {
        int num = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            num += str.charAt(i) * Math.pow(65536, str.length() - 1 - i);
        }
        return num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node node : nodes) {
            if (node != null)
                sb.append("(").append(node.key).append(",").append(node.value).append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        OpenAddressingHashTable<String, String> oaHashTable = new OpenAddressingHashTable<>(4);
        oaHashTable.put("姓名", "小杰")
                .put("年龄", "20")
//                .put("成绩", "90")
                .put("姓名", "小李")
                .put("性别", "男")
                .put("喜好", "足球");
        System.out.println(oaHashTable);
        if (oaHashTable.delete("成绩") != null)
            oaHashTable.put("所在地", "中国");
        System.out.println(oaHashTable);
    }


}
