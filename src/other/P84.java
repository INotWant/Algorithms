package other;

public class P84 {

    /* // O(n^2)
    public int largestRectangleArea(int[] heights) {
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            int min = heights[i];
            for (int j = i; j < heights.length; j++) {
                if (heights[j] < min)
                    min = heights[j];
                if (min * (j - i + 1) > area)
                    area = min * (j - i + 1);
            }
        }
        return area;
    }
    */

    // O(nlg2n)
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;
        return helper(0, heights.length - 1, heights);
    }

    private static int helper(int start, int end, int[] heights) {
        if (start == end)
            return heights[start];
        int mid = (start + end) / 2;
        int left = helper(start, mid, heights);
        int right = helper(mid + 1, end, heights);

        int min = Math.min(heights[mid], heights[mid + 1]);
        int count = 2;
        int merge = min * count;
        int i = mid - 1;
        int j = mid + 2;
        while (i >= start || j <= end) {
            ++count;
            if (i < start || (j <= end && heights[i] <= heights[j])) {
                min = Math.min(min, heights[j]);
                if (min * count > merge)
                    merge = min * count;
                ++j;
            } else {
                min = Math.min(min, heights[i]);
                if (min * count > merge)
                    merge = min * count;
                --i;
            }
        }
        return left > right ? (left > merge ? left : merge) : (right > merge ? right : merge);
    }

    public static void main(String[] args) {
        P84 p84 = new P84();
        int[] heights = {2, 1, 5, 6, 2};
        System.out.println(p84.largestRectangleArea(heights));
    }

}
