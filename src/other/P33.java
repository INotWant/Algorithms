package other;

/**
 * url :: https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 * @author kissx on 2018/2/22.
 */
public class P33 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int start = 0;
        int end = nums.length - 1;
        int pos = (start + end) / 2;
        while (true) {
            if (pos == 0 || nums[pos] < nums[pos - 1]) {
                if (nums.length == 2 && nums[0] > nums[1])
                    pos = 1;
                break;
            }
            if (start == end) {
                if (end + 1 < nums.length && nums[end + 1] < nums[end])
                    pos = end + 1;
                else
                    pos = 0;
                break;
            }
            if (nums[pos] > nums[start]) {
                start = pos;
            } else
                end = pos;
            pos = (start + end) / 2;
        }
        if (pos != 0) {
            if (nums[0] <= target) {
                start = 0;
                end = pos - 1;
            } else {
                start = pos;
                end = nums.length - 1;
            }
        } else {
            start = 0;
            end = nums.length - 1;
        }
        pos = (start + end) / 2;
        while (true) {
            if (start > end)
                return -1;
            if (nums[pos] == target)
                return pos;
            else if (nums[pos] > target)
                end = pos - 1;
            else
                start = pos + 1;
            pos = (start + end) / 2;
        }
    }

    public static void main(String[] args) {
        P33 p33 = new P33();
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int target = 3;
//        int[] nums = {1, 3, 4};
//        int target = 4;
        int[] nums = {3, 5, 1};
        int target = 1;
        System.out.println(p33.search(nums, target));
    }
}
