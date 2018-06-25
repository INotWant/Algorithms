package other;

import java.util.Arrays;

public class P88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        int num1;
        int num2;
        int iSum = 0;
        while (i <= m + n - 1) {
            if (iSum == m) {
                for (int k = j; k < n; k++)
                    nums1[i++] = nums2[k];
                break;
            }
            if (j == n)
                break;
            num1 = nums1[i];
            num2 = nums2[j];
            if (num2 < num1) {
                int count = 1;
                while ((j + count) < n && nums2[j + count] < num1)
                    ++count;
                for (int k = m - iSum - 1; k >= 0; k--)
                    nums1[i + k + count] = nums1[i + k];
                for (int k = i; k < i + count; k++)
                    nums1[k] = nums2[j++];
                i += count;
            } else {
                ++i;
                ++iSum;
            }
        }
    }

    public static void main(String[] args) {
        P88 p88 = new P88();
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int[] nums2 = {2, 5, 6};

//        int[] nums1 = {-1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0};
//        int[] nums2 = {-1, -1, 0, 0, 1, 2};
//        p88.merge(nums1, 5, nums2, 6);

        int[] nums1 = {0};
        int[] nums2 = {1};
        p88.merge(nums1, 0, nums2, 1);

//        int[] nums1 = {1, 0};
//        int[] nums2 = {2};
//        p88.merge(nums1, 1, nums2, 1);

        System.out.println(Arrays.toString(nums1));
    }

}
