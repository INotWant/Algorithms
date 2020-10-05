package other;

import java.util.Arrays;

public class P1419 {

    public static int minNumberOfFrogs(String croakOfFrogs) {
        int[] nums = new int[4];
        int max = -1;
        for (char c : croakOfFrogs.toCharArray()) {
            int flag = getFlag(c);
            if (flag == 0) {
                nums[0] += 1;
                int sum = 0;
                for (int i = 0; i < 4; i++)
                    sum += nums[i];
                max = Math.max(max, sum);
            } else {
                if (nums[flag - 1] == 0)
                    return -1;
                else {
                    --nums[flag - 1];
                    if (flag < 4)
                        ++nums[flag];
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 4; i++)
            sum += nums[i];
        if (sum != 0)
            return -1;
        return max;
    }

    private static int getFlag(char c) {
        if (c == 'c')
            return 0;
        if (c == 'r')
            return 1;
        if (c == 'o')
            return 2;
        if (c == 'a')
            return 3;
        if (c == 'k')
            return 4;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minNumberOfFrogs("croakcroak"));
        System.out.println(minNumberOfFrogs("crcoakroak"));
        System.out.println(minNumberOfFrogs("croakcrook"));
        System.out.println(minNumberOfFrogs("croakcroa"));
    }

}
