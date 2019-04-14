package other;

public class P154 {

    public int findMin(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int mid;
        while (i < j) {
            mid = (i + j) >>> 1;
            if (nums[mid] > nums[j])
                i = mid + 1;
            else if (nums[mid] < nums[j])
                j = mid;
            else {
                boolean flag = true;
                for (int k = mid + 1; k < j; k++)
                    if (nums[k] != nums[j]) {
                        flag = false;
                        break;
                    }
                if (flag)
                    j = mid;
                else
                    i = mid + 1;
            }
        }
        return nums[i];
    }

}
