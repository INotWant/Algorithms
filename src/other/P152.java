package other;

public class P152 {

    // 方法一
    /*
    public int maxProduct(int[] nums) {
        if (1 == nums.length) {
            return nums[0];
        }
        int result = Integer.MIN_VALUE;
        int tempResult = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                tempResult *= nums[i];
                if (tempResult > result) {
                    result = tempResult;
                }
            } else if (0 == nums[i]) {
                tempResult = 1;
                if (result < 0) {
                    result = 0;
                }
            } else {
                tempResult = 1;
                if (result < 0) {
                    if (i > 0) {
                        result = nums[i] * nums[i - 1] > result ? nums[i] * nums[i - 1] : result;
                    }
                    if (i < nums.length - 1) {
                        result = nums[i] * nums[i + 1] > result ? nums[i] * nums[i + 1] : result;
                    }
                }
            }
        }
        List<Integer> npList = new ArrayList<>();
        int start = 0, end;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                npList.add(i);
            } else if (nums[i] == 0) {
                end = i - 1;
                tempResult = 1;
                if (npList.size() > 1) {
                    if (npList.size() % 2 == 0) {
                        for (int j = start; j <= end; j++) {
                            tempResult *= nums[j];
                        }
                    } else {
                        int tResult1 = 1, tResult2 = 1;
                        for (int j = start; j < npList.get(npList.size() - 1); j++) {
                            tResult1 *= nums[j];
                        }
                        for (int j = npList.get(0) + 1; j <= end; j++) {
                            tResult2 *= nums[j];
                        }
                        tempResult = tResult1 > tResult2 ? tResult1 : tResult2;
                    }
                    result = tempResult > result ? tempResult : result;
                }
                npList.clear();
                start = i + 1;
            }
        }
        tempResult = 1;
        end = nums.length - 1;
        if (npList.size() > 1) {
            if (npList.size() % 2 == 0) {
                for (int j = start; j <= end; j++) {
                    tempResult *= nums[j];
                }
            } else {
                int tResult1 = 1, tResult2 = 1;
                for (int j = start; j < npList.get(npList.size() - 1); j++) {
                    tResult1 *= nums[j];
                }
                for (int j = npList.get(0) + 1; j <= end; j++) {
                    tResult2 *= nums[j];
                }
                tempResult = tResult1 > tResult2 ? tResult1 : tResult2;
            }
            result = tempResult > result ? tempResult : result;
        }
        return result;
    }
    // */

    // 方法二
    public int maxProduct(int[] nums) {
        // 一个保存最大的，一个保存最小的
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int num : nums) {
            if (num < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            // 如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);

            max = Math.max(max, imax);
        }
        return max;
    }

}
