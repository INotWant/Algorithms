package other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iwant
 * @date 19-7-1 19:23
 * @desc
 */
public class P228 {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;

        if (nums.length == 1) {
            result.add(String.valueOf(nums[0]));
            return result;
        }

        int start = nums[0];
        int last = start;
        for (int i = 1; i < nums.length; i++) {
            if (i == nums.length - 1) {
                if (nums[i] == last + 1)
                    result.add(start + "->" + (last + 1));
                else {
                    if (start == last)
                        result.add(String.valueOf(start));
                    else
                        result.add(start + "->" + last);
                    result.add(String.valueOf(nums[i]));
                }

            } else if (nums[i] == last + 1)
                ++last;
            else {
                if (start == last)
                    result.add(String.valueOf(start));
                else
                    result.add(start + "->" + last);
                start = nums[i];
                last = start;
            }
        }
        return result;
    }

}
