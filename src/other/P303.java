package other;

public class P303 {

    private static class NumArray {

        private int[] sumNums;

        public NumArray(int[] nums) {
            this.sumNums = new int[nums.length];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                this.sumNums[i] = sum;
            }
        }

        public int sumRange(int i, int j) {
            if (i == 0)
                return this.sumNums[j];
            return this.sumNums[j] - this.sumNums[i - 1];
        }

    }

}
