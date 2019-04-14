package other;

public class P161 {

    private int[] findMax(int[] nums, int start, int end) {
        int[] result = new int[2];
        if (end - start == 0) {
            result[0] = start;
            result[1] = nums[start];
        } else {
            int mid = (start + end) >>> 1;
            int[] t1Result = findMax(nums, start, mid);
            int[] t2Result = findMax(nums, mid + 1, end);
            result[0] = t1Result[1] > t2Result[1] ? t1Result[0] : t2Result[0];
            result[1] = t1Result[1] > t2Result[1] ? t1Result[1] : t2Result[1];
        }
        return result;
    }

    public int findPeakElement(int[] nums) {
        return findMax(nums, 0, nums.length - 1)[0];
    }
}
