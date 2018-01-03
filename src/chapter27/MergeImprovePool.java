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
public class MergeImprovePool implements MergeSort {

    private ExecutorService executorService;


    public MergeImprovePool() {
        this.executorService = Executors.newCachedThreadPool();
    }

    private void merge(int s1, int e1, int s2, int e2, int[] array, int start, int[] saveArray) {
        // 1, make a1 is bigger than a2
        if (e2 - s2 > e1 - s1) {
            int temp = s1;
            s1 = s2;
            s2 = temp;
            temp = e1;
            e1 = e2;
            e2 = temp;
        }
        // 0, solve little problem
        if (e1 - s1 == -1)
            return;
        // 2, get median of the a1
        int medianPos = (s1 + e1) / 2;
        int median = array[medianPos];
        // 3, get pos and redistribute
        int pos = getPos(median, s2, e2, array);
        int newMedianPos = start + (medianPos - s1) + (pos - s2);
        saveArray[newMedianPos] = array[medianPos];
        int finalS = s1;
        int finalS1 = s2;

        AtomicBoolean flag = new AtomicBoolean(false);
        this.executorService.execute(() -> {
            merge(finalS, medianPos - 1, finalS1, pos - 1, array, start, saveArray);
            flag.getAndSet(true);
        });
        merge(medianPos + 1, e1, pos, e2, array, newMedianPos + 1, saveArray);
        while (!flag.get()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * maybe should via class libraries
     *
     * @return position
     */
    private static int getPos(int num, int start, int end, int[] array) {
        while (end >= start) {
            int mid = (end - start) / 2 + start;
            if (array[mid] >= num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    @Override
    public int[] mergeSortFunction(int[] array) {
        int[] saveArray = new int[array.length];
        mergeSort(array, 0, array.length - 1, saveArray);

        /*
        while (((ThreadPoolExecutor) this.executorService).getActiveCount() > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // */

        this.executorService.shutdown();

        return saveArray;
    }

    private void mergeSort(int[] array, int s, int e, int[] saveArray) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        AtomicBoolean needWait = new AtomicBoolean(true);
        if (s != e) {
            int mid = (s + e) / 2;
            int[] t = new int[array.length];
            this.executorService.execute(() -> {
                try {
                    mergeSort(array, s, mid, t);
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
            mergeSort(array, mid + 1, e, t);
            try {
                reentrantLock.lock();
                if (needWait.get())
                    condition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
            merge(s, mid, mid + 1, e, t, s, saveArray);
        } else
            saveArray[s] = array[s];
    }

    public static void main(String[] args) {
        int[] array = {2, 9, 1, 8, 2, 10, 18, 21, 12, 5, 7, 4, 3};
        MergeImprovePool mergeImprovePool = new MergeImprovePool();
        int[] saveArray = mergeImprovePool.mergeSortFunction(array);
        System.out.println(Arrays.toString(saveArray));
    }
}
