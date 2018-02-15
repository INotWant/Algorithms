package other;

/**
 * url :: https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 *
 * @author kissx on 2018/2/15.
 */
public class P26 {

    // 绕弯路了~~~
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int size = 1;
        int last = nums[0];
        int count = 1;
        int temp;
        while (count < nums.length) {
            if (last != nums[size]) {
                last = nums[size];
                ++size;
                ++count;
            } else {
                temp = size;
                while (temp < nums.length && last == nums[temp]) {
                    ++temp;
                    ++count;
                }
                for (int i = temp; i < nums.length; i++)
                    nums[size + i - temp] = nums[i];
            }
        }
        return size;
    }

    public static void main(String[] args) {
        P26 p26 = new P26();
        int count = p26.removeDuplicates(new int[]{1, 2, 2, 5, 5, 5, 10});
        System.out.println(count);
    }

}
