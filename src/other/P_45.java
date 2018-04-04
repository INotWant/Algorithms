package other;

public class P45 {

    public int jump(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; ) {
            int pNum = nums[i];
            if (pNum + i >= nums.length - 1) {
                ++count;
                break;
            }
            int max = -1;
            int tempI = -1;
            for (int j = i + 1; j <= pNum + i && j < nums.length; j++) {
                if (nums[j] + j > max) {
                    max = nums[j] + j;
                    tempI = j;
                }
            }
            ++count;
            i = tempI;
        }
        return count;
    }

}
