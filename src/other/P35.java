package other;

/**
 * @author kissx on 2018/2/24.
 */
public class P35 {

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int start = 0;
        int end = nums.length - 1;
        int middle = (start + end) / 2;
        while (end >= start) {
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target)
                start = middle + 1;
            else
                end = middle - 1;
            middle = (start + end) / 2;
        }
        return start;
    }

    public static void main(String[] args) {
        P35 p35 = new P35();
        int[] array = new int[]{2, 4, 5, 6};
        int target = 7;
        System.out.println(p35.searchInsert(array, target));
    }

}
