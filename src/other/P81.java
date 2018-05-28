package other;

public class P81 {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int startPos = 0;
        int endPos = nums.length - 1;
        int midPos = (startPos + endPos) / 2;
        while (endPos > startPos) {
            midPos = (startPos + endPos) / 2;
            if (nums[midPos] > nums[startPos]) {
                startPos = midPos;
            } else if (nums[midPos] < nums[startPos]) {
                endPos = midPos;
            } else {
                for (int i = startPos + 1; i < midPos; i++) {
                    if (nums[i] != nums[midPos]) {
                        endPos = midPos;
                        break;
                    }
                }
                if (endPos != midPos)
                    startPos = midPos;
            }
            if (endPos - startPos < 5) {
                midPos = startPos;
                for (int i = startPos + 1; i < endPos; i++) {
                    if (nums[i] > nums[i + 1]) {
                        midPos = i;
                        break;
                    }
                }
                break;
            }
        }
        if (half(0, nums.length - 1, nums, target))
            return true;
        if (target >= nums[0]) {
            if (target > nums[midPos])
                return false;
            return half(0, midPos, nums, target);
        } else {
            if (midPos + 1 >= nums.length || target < nums[midPos + 1])
                return false;
            return half(midPos + 1, nums.length - 1, nums, target);
        }
    }

    private static boolean half(int start, int end, int[] array, int target) {
        while (end >= start) {
            if (end == start)
                return array[start] == target;
            int mid = (start + end) / 2;
            if (array[mid] == target)
                return true;
            if (array[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    /* // 一个很好的方法
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] > nums[left]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else // 感觉这里用的很妙
                left++;
        }
        return false;
    }
    // */


    public static void main(String[] args) {
        P81 p81 = new P81();

//        int[] nums = {2, 2, 2, 0, 2, 2};
//        int target = 0;

        int[] nums = {1, 3, 1, 1, 1, 1};
        int target = 3;

        System.out.println(p81.search(nums, target));
    }

}
