package other;

import java.util.Arrays;

public class P912 {

    public int[] sortArray(int[] nums) {
//        mergeSort(nums, 0, nums.length - 1);
//        mergeSortInPlace(nums, 0, nums.length - 1);
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 归并算法
     * 最坏：O(nlgn)
     * 是否稳定：是
     * 空间：O(nlgn)
     */
    private void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);

        int[] tmpArr = new int[end - start + 1];

        int i = start;
        int j = mid + 1;
        int k = 0;

        for (; i <= mid && j <= end; k++) {
            if (nums[i] > nums[j]) {
                tmpArr[k] = nums[j];
                ++j;
            } else {
                tmpArr[k] = nums[i];
                ++i;
            }
        }
        while (i <= mid) {
            tmpArr[k++] = nums[i++];
        }
        while (j <= end) {
            tmpArr[k++] = nums[j++];
        }
        k = 0;
        for (int l = start; l <= end; l++) {
            nums[l] = tmpArr[k++];
        }
    }

    /**
     * 归并排序原地版
     * 思路：循环不定式（i前面都有序）+ 交换内存
     */
    private void mergeSortInPlace(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSortInPlace(nums, start, mid);
        mergeSortInPlace(nums, mid + 1, end);

        int i = start;
        int j = mid + 1;
        int index;

        while (i < j && j <= end) {
            if (nums[i] <= nums[j]) {
                ++i;
            } else {
                index = j;
                while (j <= end && nums[i] > nums[j]) {
                    ++j;
                }
                --j;
                swapMemo(nums, i, index - 1, index, j);
                i += (++j - index);
            }
        }

    }

    private void swapMemo(int[] nums, int s1, int e1, int s2, int e2) {
        assert s2 == e1 + 1;
        reverse(nums, s1, e1);
        reverse(nums, s2, e2);
        reverse(nums, s1, e2);
    }

    private void reverse(int[] nums, int start, int end) {
        int i = start, j = end;
        int tmp;
        while (i < j) {
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            ++i;
            --j;
        }
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        } else {
            int tmp = nums[start];
            int i = start;
            int j = end;

            while (i < j) {
                while (j > i && nums[j] > tmp) {
                    --j;
                }
                if (j > i) {
                    nums[i] = nums[j];
                    ++i;
                }
                while (i < j && nums[i] < tmp) {
                    ++i;
                }
                if (i < j) {
                    nums[j] = nums[i];
                    --j;
                }
            }
            nums[i] = tmp;
            quickSort(nums, start, i - 1);
            quickSort(nums, i + 1, end);
        }
    }

    public static void main(String[] args) {
        P912 p912 = new P912();
//        int[] nums = {5, 2};
        int[] nums = {5, 2, 3, 1};
//        int[] nums = {5, 1, 1, 2, 0, 0};
//        int[] nums = {-1, 2, -8, -10};
//        int[] nums = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};

//        int[] nums = {-2, 3, -5};
        System.out.println(Arrays.toString(p912.sortArray(nums)));
    }

}
