package other;

public class P96 {

    /*
    private static class Result {
        static int total;
    }

    public int numTrees(int n) {
        if (n == 1)
            return 1;
        int[] array = new int[(int) Math.pow(2, n)];
        Arrays.fill(array, -1);
        array[1] = 1;
        Result.total = 0;
        helper(1, 1, 1, n, array);
        return Result.total;
    }

    private void helper(int pos, int endPos, int count, int n, int[] array) {
        for (; pos <= endPos; ++pos) {
            if (array[pos] != -1) {
                for (int j = 1; j <= 2; j++) {
                    if (j == 1) {
                        ++count;
                        if (count == n) {
                            Result.total += 2;
                            --count;
                            break;
                        } else if (count > n) {
                            return;
                        } else {
                            array[pos * 2] = count;
                            helper(pos + 1, endPos > pos * 2 ? endPos : (pos * 2), count, n, array);
                            array[pos * 2] = -1;
                            array[pos * 2 + 1] = count;
                            helper(pos + 1, endPos > pos * 2 + 1 ? endPos : (pos * 2 + 1), count, n, array);
                            array[pos * 2 + 1] = -1;
                            --count;
                        }
                    } else if (count > n) {
                        return;
                    } else {
                        count += 2;
                        if (count == n) {
                            ++Result.total;
                            count -= 2;
                            break;
                        } else {
                            array[pos * 2] = count - 1;
                            array[pos * 2 + 1] = count;
                            helper(pos + 1, endPos > pos * 2 + 1 ? endPos : (pos * 2 + 1), count, n, array);
                            array[pos * 2] = -1;
                            array[pos * 2 + 1] = -1;
                            count -= 2;
                        }
                    }
                }
            }
        }
    }
    // */

    public int numTrees(int n) {
        if (n <= 0)
            return 0;
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                array[i] += array[j] * array[i - 1 - j];
            }
        }
        return array[n];
    }

    public static void main(String[] args) {
        P96 p96 = new P96();
        System.out.println(p96.numTrees(5));
    }

}
