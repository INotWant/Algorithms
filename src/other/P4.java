package other;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * url: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 *
 * @author kissx on 2018/1/31.
 */
public class P4 {

    private static int binaryFind(int[] array, int start, int end, int value) {
        if (end >= start) {
            int mid = (start + end) / 2;
            if (array[mid] > value)
                return binaryFind(array, start, mid - 1, value);
            else if (array[mid] == value || (mid + 1 > end || array[mid + 1] >= value))
                return mid;
            else
                return binaryFind(array, mid + 1, end, value);
        } else
            return start - 1;
    }

    private static double findMedianSortedArraysHelper(int[] nums1, int[] nums2, int s1, int e1, int s2, int e2, int pos) {
        if (e1 == s1 && e2 == s2)
            if (pos == 1)
                return nums1[s1] > nums2[s2] ? nums2[s2] : nums1[s1];
            else
                return nums1[s1] < nums2[s2] ? nums2[s2] : nums1[s1];
        int mid = (s1 + e1) / 2;
        int value = nums1[mid];
        int point = binaryFind(nums2, s2, e2, value);
        int lNumber = point - s2 + mid - s1 + 2;
        if (pos > lNumber) {
            // in right
            int n1 = e1 - mid;
            int n2 = e2 - point;
            if (n1 >= n2)
                return findMedianSortedArraysHelper(nums1, nums2, mid + 1, e1, point + 1, e2, pos - lNumber);
            else
                return findMedianSortedArraysHelper(nums2, nums1, point + 1, e2, mid + 1, e1, pos - lNumber);
        } else if (pos < lNumber) {
            int n1 = mid - s1 + 1;
            int n2 = point - s2 + 1;
//            if (n1 > n2 || (n1 == n2 && nums1[mid + n1 / 2] < nums2[point + n1 / 2]))
            if (n1 >= n2)
                return findMedianSortedArraysHelper(nums1, nums2, s1, mid, s2, point, pos);
            else
                return findMedianSortedArraysHelper(nums2, nums1, s2, point, s1, mid, pos);
        } else
            return nums1[mid];
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        if (size % 2 == 0) {
            int p1 = size / 2;
            int p2 = size / 2 + 1;
            if (nums2.length > nums1.length)
                return (findMedianSortedArraysHelper(nums2, nums1, 0, nums2.length - 1, 0, nums1.length - 1, p1) +
                        findMedianSortedArraysHelper(nums2, nums1, 0, nums2.length - 1, 0, nums1.length - 1, p2)) / 2.;
            else
                return (findMedianSortedArraysHelper(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, p1) +
                        findMedianSortedArraysHelper(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, p2)) / 2.;
        } else {
            if (nums2.length > nums1.length)
                return findMedianSortedArraysHelper(nums2, nums1, 0, nums2.length - 1, 0, nums1.length - 1, (size + 1) / 2);
            else
                return findMedianSortedArraysHelper(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, (size + 1) / 2);
        }
    }


    public static void main(String[] args) {

        // test binaryFind
//        int[] array = {1, 3, 3, 3, 6, 8, 10};
//        int value = 0;
//        System.out.println("pos :: " + binaryFind(array, 0, array.length - 1, value));

        // test
//        int[] nums1 = {1, 2};
//        int[] nums2 = {3, 4};

//        int[] nums1 = {1, 3};
//        int[] nums2 = {2};

//        int[] nums1 = {2};
//        int[] nums2 = {1, 3};

        int[] nums1 = {1, 1};
        int[] nums2 = {1, 1};
        System.out.println("result :: " + findMedianSortedArrays(nums1, nums2));
    }

}
