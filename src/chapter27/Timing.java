package chapter27;

/**
 * timing
 *
 * @author kissx on 2017/2/12.
 */
public class Timing {

    private MergeSort mergeSort;

    public Timing(MergeSort mergeSort) {
        this.mergeSort = mergeSort;
    }

    public int[] countTime(int[] array) {
        long start = System.currentTimeMillis();
        int[] arraySorted = this.mergeSort.mergeSortFunction(array);
        long endTime = System.currentTimeMillis();
        System.out.println("【" + this.mergeSort.getClass().getName() + "】" + "spend time :: " + (endTime - start) / 1000.);
        return arraySorted;
    }
}
