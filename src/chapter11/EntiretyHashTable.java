package chapter11;

import java.util.Arrays;
import java.util.Random;

/**
 * 完全散列（二次散列）
 * 特点：只针对静态集合，且查找时间为 O（1）
 * 实现缺陷：未考虑空间复杂度，要想达到 O(n) 也需要随机
 *
 * @author kissx on 2017/10/11.
 */
public class EntiretyHashTable<K, V> {

    private int m;  // 槽的大小
    private int p;  // 全域散列函数中较大的素数
    private int a;  // 用于全域散列函数
    private int b;  // 用于全域散列函数
    private int[][] kArray;    // 存放 key 在二次散列中使用的 mm aa bb
    private V[][] vArray;      // 存放 value 与 key 的下标对应

    private Random random = new Random();

    @SuppressWarnings("unchecked")
    public EntiretyHashTable(int m) {
        this.m = m;
        this.kArray = new int[m][3];
        this.vArray = (V[][]) new Object[m][];
        this.p = getMorePrime(m * m);
        this.a = random.nextInt(p - 1) + 1;
        this.b = random.nextInt(p);
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
     * 哈希函数（全域散列函数类 [((ak + b)mod P)mod m] ）
     */
    private int hashFunction(K key, int p, int m, int a, int b) {
        int keyInteger;
        if (key instanceof Number) {
            keyInteger = ((Number) key).intValue();
        } else
            keyInteger = key.hashCode();
        return ((a * keyInteger + b) % p) % m;
    }

    /**
     * 将不会修改的 (key,value) 集合【静态数据】导入。其中，使用的散列方式为：二次散列。
     * 【注：此过程中，可能需要多次二次散列，用以保证最后的二次散列不会有冲突！】
     *
     * @param keys   key数组
     * @param values value数组
     * @return 返回 put 数据是否成功，除 key数组 与 value数组 大小不一样外，都会成功。
     */
    @SuppressWarnings("unchecked")
    public boolean put(K[] keys, V[] values) {
        if (keys.length != values.length)
            return false;
        String[] signArray = new String[m];
        for (int i = 0; i < signArray.length; i++) {
            signArray[i] = "";
        }
        // 记录第一次散列的分布
        for (int i = 0; i < keys.length; i++)
            signArray[hashFunction(keys[i], p, m, a, b)] += i + ",";
        for (int i = 0; i < signArray.length; i++) {
            String sign = signArray[i];
            if (sign.length() > 0) {
                boolean entirety = false;
                while (!entirety) {
                    entirety = true;
                    String[] tArray = sign.split(",");
                    int num = tArray.length;
                    if (num == 1) {
                        kArray[i][0] = 1;
                        kArray[i][1] = 0;
                        kArray[i][2] = 0;
                        vArray[i] = (V[]) new Object[1];
                        vArray[i][0] = values[Integer.parseInt(tArray[0])];
                    } else {
                        // 二次散列
                        int aa = random.nextInt(p - 1) + 1;
                        int bb = random.nextInt(p);
                        int[] pArray = new int[num * num];
                        Arrays.fill(pArray, -1);
                        for (String aTArray : tArray) {
                            int pos = hashFunction(keys[Integer.parseInt(aTArray)], p, num * num, aa, bb);
                            if (pArray[pos] != -1) {
                                entirety = false;
                                break;
                            }
                            pArray[pos] = Integer.parseInt(aTArray);
                        }
                        if (entirety) {
                            kArray[i][0] = num * num;
                            kArray[i][1] = aa;
                            kArray[i][2] = bb;
                            for (int j = pArray.length - 1; j >= 0; j--) {
                                if (pArray[j] != -1) {
                                    if (vArray[i] == null) {
                                        vArray[i] = (V[]) new Object[j + 1];
                                    }
                                    vArray[i][j] = values[pArray[j]];
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 由 key 查找对应的 value 值 【注：查找时需要 key 一定是已存在的，否则不存在时有一定几率返回不为 NULL】
     *
     * @param key key 值
     * @return 对应的 value 值
     */
    public V search(K key) {
        int pos = hashFunction(key, p, m, a, b);
        int mm = kArray[pos][0];
        int aa = kArray[pos][1];
        int bb = kArray[pos][2];
        int newPos = hashFunction(key, p, mm, aa, bb);
        return vArray[pos][newPos];
    }

    public static void main(String[] args) {
        String[] kArray = new String[5];
        String[] vArray = new String[5];
        kArray[0] = "141";
        kArray[1] = "121";
        kArray[2] = "131";
        kArray[3] = "145";
        kArray[4] = "140";
        vArray[0] = "lucy";
        vArray[1] = "tom";
        vArray[2] = "jack";
        vArray[3] = "lily";
        vArray[4] = "Oliver";
        EntiretyHashTable<String, String> entiretyHashTable = new EntiretyHashTable<>(10);
        entiretyHashTable.put(kArray, vArray);
        System.out.println("145 :: " + entiretyHashTable.search("145"));
    }

}
