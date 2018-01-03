package chapter27;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kissx on 2017/2/12.
 */
public class MergeSortPool implements MergeSort {

    private ExecutorService executorService;

    // 锁必须在方法内
//    private ReentrantLock reentrantLock = new ReentrantLock();
//    private Condition condition = reentrantLock.newCondition();

    public MergeSortPool() {
//        this.executorService = Executors.newFixedThreadPool(threadNum);
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public int[] mergeSortFunction(int[] array) {
        int[] myArray = Arrays.copyOf(array, array.length);
        mergeSort(myArray, 0, myArray.length - 1);
        this.executorService.shutdown();
        return myArray;
    }

    // TODO 若线程池设置的太小，则会导致死锁。因为，会出现把所有的线程
    private void mergeSort(int[] array, int s, int e) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        AtomicBoolean needWait = new AtomicBoolean(true);
        if (s != e) {
            int mid = (s + e) / 2;
            this.executorService.execute(() -> {
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
            });
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
