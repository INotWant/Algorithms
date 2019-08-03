package other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iwant
 * @date 19-7-1 19:35
 * @desc
 */
public class P229 {

    // 摩尔投票算法
    // http://www.cs.utexas.edu/users/moore/best-ideas/mjrty/index.html
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;

        int mj1 = 0, mj2 = 0, cn1, cn2;
        cn1 = 0;
        cn2 = 0;
        for (int num : nums) {
            if (cn1 == 0 && mj2 != num) {
                mj1 = num;
                ++cn1;
            } else if (cn2 == 0 && mj1 != num) {
                mj2 = num;
                ++cn2;
            } else if (num == mj1)
                ++cn1;
            else if (num == mj2)
                ++cn2;
            else {
                --cn1;
                --cn2;
            }
        }
        cn1 = 0;
        cn2 = 0;
        for (int num : nums)
            if (mj1 == num)
                ++cn1;
            else if (mj2 == num)
                ++cn2;
        if (cn1 > nums.length / 3)
            result.add(mj1);
        if (cn2 > nums.length / 3)
            result.add(mj2);
        return result;
    }

}
