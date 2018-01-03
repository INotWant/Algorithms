package chapter27;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kissx on 2017/2/12.
 */
public class MergeSortThread implements MergeSort {

    @Override
    public int[] mergeSortFunction(int[] array) {
        int[] myArray = Arrays.copyOf(array, array.length);
        mergeSort(myArray, 0, myArray.length - 1);
        return myArray;
    }

    private void mergeSort(int[] array, int s, int e) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        // 只要保证 needWait 是“内部”的，就可以使得排序不错乱
        AtomicBoolean needWait = new AtomicBoolean(true);
        if (s != e) {
            int mid = (s + e) / 2;
            new Thread(() -> {
                try {
                    mergeSort(array, s, mid);
                } finally {
                    try {
                        reentrantLock.lock();
                        needWait.set(false);
                        condition.signal();
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }).start();
            mergeSort(array, mid + 1, e);
            try {
                reentrantLock.lock();
                if (needWait.get())
                    condition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
            merge(array, s, mid, e);
        }
    }

    private void merge(int[] array, int s, int mid, int e) {
        int[] a1 = Arrays.copyOfRange(array, s, mid + 1);
        int[] a2 = Arrays.copyOfRange(array, mid + 1, e + 1);
        int j = 0;
        int k = 0;
        for (int i = s; i <= e; i++) {
            if (j >= a1.length) {
                array[i] = a2[k];
                ++k;
                continue;
            }
            if (k >= a2.length) {
                array[i] = a1[j];
                ++j;
                continue;
            }
            if (a1[j] < a2[k]) {
                array[i] = a1[j];
                ++j;
            } else {
                array[i] = a2[k];
                ++k;
            }
        }
    }

}
