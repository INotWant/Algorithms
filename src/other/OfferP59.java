package other;

public class OfferP59 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[0];

        int[] result = new int[nums.length - k + 1];
        int index = 0;
        int last = -1, lastMax = -1;
        for (int i = k - 1; i < nums.length; i++) {
            int max;
            if (last != -1 && last >= i - k + 1 && nums[i] < lastMax)
                max = lastMax;
            else {
                int tIndex = i - k + 1;
                max = nums[tIndex];
                for (int j = i - k + 2; j <= i; j++)
                    if (nums[j] > max) {
                        max = nums[j];
                        tIndex = j;
                    }
                last = tIndex;
                lastMax = max;
            }
            result[index++] = max;
        }
        return result;
    }

}
