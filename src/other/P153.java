package other;

public class P153 {

    // 弱项 -- 二分边界（想着想着就乱了）
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1, mid;
        while (r > l) {
            mid = (l + r) >>> 1;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {1, 2, 3, 4, 5};
        P153 p153 = new P153();
        System.out.println(p153.findMin(nums));
    }

}
