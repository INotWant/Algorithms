package chapter6;

/**
 * @author kissx on 2017/9/20.
 * 利用 最大堆 逆序输出
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        Heap heap = new Heap(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(heap.delete(0));
        }
    }

}
