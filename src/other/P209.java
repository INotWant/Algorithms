package other;

public class P209 {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] a1 = new int[nums.length];
        int[] a2 = new int[nums.length];

        a1[0] = 0;
        a2[0] = nums[0];
        if (a2[0] >= s)
            return 1;

        int max = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            if (n >= s)
                return 1;
            a2[i] = a2[i - 1] + nums[i];
            if (a2[i] >= s) {
                int j = a1[i - 1];
                while (a2[i] >= s)
                    a2[i] -= nums[j++];
                a1[i] = --j;
                a2[i] += nums[j];
                if (i - j + 1 < max)
                    max = i - j + 1;
            } else
                a1[i] = a1[i - 1];
        }
        return max == Integer.MAX_VALUE ? 0 : max;
    }

}
