package other;

import java.util.Arrays;

/**
 * url :: https://leetcode.com/problems/next-permutation/description/
 * <p>
 * 心得：做了两遍，现在的我还是急于编程啊！
 *
 * @author kissx on 2018/2/18.
 */
public class P31 {

    public void nextPermutation(int[] nums) {
        // 1, 判断是否是最大的
        boolean isBig = true;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                isBig = false;
                break;
            }
        }
        int temp;
        if (isBig) {
            for (int i = 0; i < nums.length / 2; i++) {
                temp = nums[i];
                nums[i] = nums[nums.length - i - 1];
                nums[nums.length - i - 1] = temp;
            }
        } else {
            int pos = 0;
            while (true) {
                // 2, 再看低位（n-1）是否是最大的
                isBig = true;
                for (int i = nums.length - 1; i > pos + 1; i--) {
                    if (nums[i - 1] < nums[i]) {
                        isBig = false;
                        break;
                    }
                }
                if (!isBig) {
                    ++pos;
                } else {
                    int start = nums[pos];
                    Arrays.sort(nums, pos, nums.length);
                    for (int i = pos; i < nums.length; i++) {
                        if (nums[i] > start) {
                            start = nums[i];
                            System.arraycopy(nums, pos, nums, pos + 1, i - pos);
                            nums[pos] = start;
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        P31 p31 = new P31();
        int[] array = {2, 3, 1};
        p31.nextPermutation(array);
        System.out.println(Arrays.toString(array));
    }


}
