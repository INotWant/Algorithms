package other;

public class _leetcode_2_1 {

    private static class Node {
        int k;
        int pos;

        Node(int k, int pos) {
            this.k = k;
            this.pos = pos;
        }
    }

    private Node helper(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            int count = nums[mid] - nums[l] - mid + l;
            if (count == 0)
                break;
            if (count < k) {
                l = mid;
                k -= count;
            } else
                r = mid;
        }
        return new Node(k, l);
    }


    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        int totalCount = nums[len - 1] - nums[0] + 1 - len;
        if (k > totalCount)
            return nums[len - 1] + k - totalCount;
        else {
            Node node = helper(nums, k);
            int i = node.pos;
            for (; i < nums.length - 1; i++) {
                if (nums[i + 1] - nums[i] > 1)
                    break;
            }
            return nums[i] + node.k;
        }
    }

    public static void main(String[] args) {
        _leetcode_2_1 obj = new _leetcode_2_1();

        int[] nums = {4, 7, 9, 10, 13};
        int k = 3;

//        int[] nums = {1, 2, 4};
//        int k = 1;

        System.out.println(obj.missingElement(nums, k));
    }

}
