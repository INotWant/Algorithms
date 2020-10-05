package other;

public class P238 {

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int[] l = new int[nums.length];
        l[0] = 1;
        int[] r = new int[nums.length];
        r[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; i++)
            l[i] = l[i - 1] * nums[i - 1];
        for (int i = nums.length - 2; i >= 0; i--)
            r[i] = r[i + 1] * nums[i + 1];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                result[i] = r[0];
            else if (i == nums.length - 1)
                result[i] = l[nums.length - 1];
            else
                result[i] = l[i] * r[i];
        }
        return result;
    }

}
