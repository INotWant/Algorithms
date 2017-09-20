package chapter6;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kissx on 2017/9/20.
 *         <p>
 *         堆（最大堆）数据结构
 */
public class Heap {

    private List<Integer> list;

    /**
     * 初始化，构造堆
     * @param list 集合
     */
    public Heap(List<Integer> list) {
        this.list = new ArrayList<>(list);
        int heapSize = this.list.size();
        for (int i = this.list.size() / 2 - 1; i >= 0; i--) {
            maxHeapify(i, heapSize);
        }
    }

    /**
     * 初始化，构造堆
     * @param array 数组
     */
    public Heap(int[] array) {
        this.list = new ArrayList<>();
        for (int n : array) {
            list.add(n);
        }
        int heapSize = this.list.size();
        for (int i = this.list.size() / 2 - 1; i >= 0; i--) {
            maxHeapify(i, heapSize);
        }
    }

    /**
     * 维护以 i 为根节点的子树（或称为子堆）
     * @param i 根节点位置
     * @param heapSize 堆的有效范围
     */
    public void maxHeapify(int i, int heapSize) {
        if (i < heapSize) {
            int maxPos = i;
            Integer lPos = getLeftChild(i);
            Integer rPos = getRightChild(i);
            if (lPos != null && list.get(i) < list.get(lPos))
                maxPos = lPos;
            if (rPos != null && list.get(rPos) > list.get(maxPos))
                maxPos = rPos;
            if (maxPos != i) {
                int temp = list.get(i);
                list.set(i, list.get(maxPos));
                list.set(maxPos, temp);
                maxHeapify(maxPos, heapSize);
            }
        }
    }

    /**
     * 向堆中插入 key
     * @param key 待插入的值
     */
    public void insert(int key) {
        list.add(Integer.MIN_VALUE);
        modify(list.size() - 1, key);
    }

    /**
     * 修改 i 结点元素的值为 key
     * @param i 待修改的结点（位置）
     * @param key 要修改成的值
     */
    public void modify(int i, int key) {
        if (i < list.size()) {
            if (list.get(i) <= key)
                plusModify(i, key);
            else if (list.get(i) > key)
                reduceModify(i, key);
        }
    }

    private void plusModify(int i, int key) {
        list.set(i, key);
        int currentPos = i;
        Integer pPos = getParent(i);
        while (pPos != null && key > list.get(pPos)) {
            list.set(currentPos, list.get(pPos));
            list.set(pPos, key);
            currentPos = pPos;
            pPos = getParent(pPos);
        }
    }

    private void reduceModify(int i, int key) {
        list.set(i, key);
        maxHeapify(i, list.size());
    }

    /**
     * 删除 i 结点
     * @param i 待删除结点的位置
     * @return 原 i 处元素的值
     */
    public Integer delete(int i) {
        if (i < list.size()) {
            int deleteElement = list.get(i);
            list.set(i, list.get(list.size() - 1));
            list.remove(list.size() - 1);
            maxHeapify(i, list.size());
            return deleteElement;
        }
        return null;
    }

    /**
     * 获得堆中最大值
     * @return 值
     */
    public Integer getMax() {
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    private Integer getLeftChild(int i) {
        if (i >= 0 && i * 2 + 1 < list.size())
            return i * 2 + 1;
        return null;
    }

    /**
     * 获得 i 结点的左孩子的值
     * @param i 位置
     * @return 值
     */
    public Integer getLeftElement(int i) {
        Integer pos = getLeftChild(i);
        if (pos != null)
            return list.get(pos);
        return null;
    }

    private Integer getRightChild(int i) {
        if (i >= 0 && (i + 1) * 2 < list.size())
            return (i + 1) * 2;
        return null;
    }

    /**
     * 获得 i 结点的右孩子的值
     * @param i 位置
     * @return 值
     */
    public Integer getRightElement(int i) {
        Integer pos = getRightChild(i);
        if (pos != null)
            return list.get(pos);
        return null;
    }

    private Integer getParent(int i) {
        if (i > 0 && i < list.size()) {
            return (i - 1) / 2;
        }
        return null;
    }

    /**
     * 获得 i 结点的父节点的值
     * @param i 位置
     * @return 值
     */
    public Integer getParentElement(int i) {
        Integer pos = getParent(i);
        if (pos != null)
            return list.get(pos);
        return null;
    }

    /**
     * 获得堆中所有元素
     * @return 数组
     */
    public int[] getElements() {
        int[] array = new int[list.size()];
        int i = 0;
        for (Integer element : list) {
            array[i++] = element;
        }
        return array;
    }

    /**
     * 获得堆中元素个数
     * @return 数目
     */
    public int getHeapSize() {
        return list.size();
    }

    /**
     * 堆输出
     */
    public void print() {
        System.out.println(helpPrint());
    }

    private String helpPrint() {
        StringBuilder stringBuilder = new StringBuilder();
        if (list.size() > 0) {
            int pos = 0;
            int m = ((Double) (Math.log(list.size()) / Math.log(2))).intValue();
            for (int i = 0; i <= m; i++) {
                for (int j = 1; j <= Math.pow(2, i) && pos < list.size(); j++) {
                    stringBuilder.append(list.get(pos++));
                    if (j != Math.pow(2, i) && pos != list.size())
                        stringBuilder.append(",");
                }
                if (i != m)
                    stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return helpPrint();
    }

}
