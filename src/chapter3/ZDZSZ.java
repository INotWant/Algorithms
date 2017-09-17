package chapter3;

/**
 * @author kissx on 2017/9/17.
 *         最大子数组问题
 */
public class ZDZSZ {

    private static class Node {
        int start;
        int end;
        int value;

        Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    private static Node zdzsz(int[] array, int start, int end) {
        if (start == end)
            return new Node(start, end, array[start]);
        else {
            int mid = (start + end) / 2;
            Node n1 = zdzsz(array, start, mid);
            Node n2 = zdzsz(array, mid + 1, end);
            Node n3 = center(array, start, mid, end);
            if (n1.value >= n2.value && n1.value >= n3.value)
                return n1;
            else if (n2.value > n1.value && n2.value > n3.value)
                return n2;
            else
                return n3;
        }
    }

    private static Node center(int[] array, int start, int mid, int end) {
        int currentMax = array[mid];
        int sum = currentMax;
        int s = mid;
        for (int i = mid - 1; i >= start; i--) {
            sum += array[i];
            if (sum > currentMax) {
                s = i;
                currentMax = sum;
            }
        }
        sum = currentMax;
        int e = mid;
        for (int i = mid + 1; i <= end; i++) {
            sum += array[i];
            if (sum > currentMax) {
                e = i;
                currentMax = sum;
            }
        }
        return new Node(s, e, currentMax);
    }

    // --------------------------------分割线--------------------------------

    /**
     * 线性时间实现
     */
    private static Node zdzszLine(int[] array) {
        int currentMax = 0;
        int s = 0;
        int e = -1;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (sum > currentMax) {
                e = i;
                currentMax = sum;
            } else if (sum < 0) {
                s = i + 1;
                sum = 0;
            }
        }
        if (e == -1) {
            currentMax = array[0];
            for (int i = 1; i < array.length; i++) {
                if (currentMax < array[i]) {
                    s = i;
                    e = i;
                    currentMax = array[i];
                }
            }
        }
        return new Node(s, e, currentMax);
    }


    public static void main(String[] args) {
        int[] array = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
//        int[] array = {-2, -1, -5};
        // 递归方法
//        Node n = zdzsz(array, 0, array.length - 1);
        // 非递归方法
        Node n = zdzszLine(array);
        System.out.println("start::" + n.start + " end::" + n.end + " value::" + n.value);
    }
}
