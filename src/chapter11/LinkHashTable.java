package chapter11;

import java.util.LinkedList;
import java.util.List;

/**
 * 链接法实现散列表
 * 实现缺陷：时间复杂度 与 装载因子 有很大关系，而实现中并未考虑装载因子
 * 解决方案：
 *     1）根据装载因子，随存储的增加动态扩展散列表的大小
 *
 * @author kissx on 2017/10/11.
 */
public class LinkHashTable<K, V> {

    private int m;
    private List<Node>[] lists;

    class Node {
        K key;
        V value;
    }

    /**
     * 构造 hashTable，需初始化槽的大小。
     *
     * @param m 槽的大小
     */
    @SuppressWarnings("unchecked")
    public LinkHashTable(int m) {
        this.m = m;
        lists = new List[m];
    }

    /**
     * 向散列表中添加数据。由于返回为调用对象，故可以连续 put() 调用。
     *
     * @param key   key 值
     * @param value value
     */
    public LinkHashTable<K, V> put(K key, V value) {
        int pos = hashFunction(key);
        if (lists[pos] == null)
            lists[pos] = new LinkedList<>();
        LinkedList<Node> list = (LinkedList<Node>) lists[pos];
        for (Node n : list) {
            if (n.key.equals(key)) {
                n.value = value;
                return this;
            }
        }
        Node node = new Node();
        node.key = key;
        node.value = value;
        list.addFirst(node);
        return this;
    }

    /**
     * 根据 key 值获取 value，返回封装的 Node 。若返回为 null，则表明为查找到。
     */
    public Node search(K key) {
        int pos = hashFunction(key);
        List<Node> list = lists[pos];
        for (Node node : list) {
            if (node.key.equals(key))
                return node;
        }
        return null;
    }

    /**
     * 根据 key 值删除，返回删除对象。如果返回 null ，表明删除失败。
     */
    public Node delete(K key) {
        int pos = hashFunction(key);
        Node node = search(key);
        if (lists[pos].remove(node))
            return node;
        else
            return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (List<Node> list : lists) {
            if (list != null)
                for (Node node : list)
                    sb.append("(").append(node.key).append(",").append(node.value).append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 乘法散列法实现哈希函数
     *
     * @param key key 值
     * @return 由 key 经哈希函数给出的位置
     */
    private int hashFunction(K key) {
        int keyInteger;
        if (key instanceof String) {
            keyInteger = str2num((String) key);
        } else if (key instanceof Number)
            keyInteger = ((Number) key).intValue();
        else
            keyInteger = key.hashCode();
        double A = (Math.sqrt(5) - 1) / 2;
        return (int) Math.floor((A * keyInteger % 1) * m);
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


    public static void main(String[] args) {
        /* 测试一
        LinkHashTable<String, String> hashTable = new LinkHashTable<>(2);
        hashTable.put("姓名", "小杰")
                .put("成绩", "90")
                .put("年龄", "20")
                .put("姓名", "大亮");
        System.out.println(hashTable);
        System.out.println("姓名：" + hashTable.search("姓名").value);
        hashTable.delete("姓名");
        System.out.println("姓名：" + hashTable.search("姓名"));
        System.out.println("成绩：" + hashTable.search("成绩").value);
        //*/
        //* 测试二
        LinkHashTable<Integer, String> hashTable = new LinkHashTable<>(5);
        hashTable.put(1, "lucy")
                .put(2, "xiaoJie")
                .put(3, "tom")
                .put(4, "jack");
        hashTable.delete(2);
        System.out.println(hashTable);
        //*/
    }
}
