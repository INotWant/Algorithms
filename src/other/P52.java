package other;

public class P52 {

    /*
    public int totalNQueens(int n) {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        int k = 0;
        while (k >= 0) {
            list.set(k, list.get(k) + 1);
            while (list.get(k) < n && !check(list))
                list.set(k, list.get(k) + 1);
            if (list.get(k) < n) {
                if (k == n - 1) {
                    ++count;
                } else {
                    ++k;
                    list.add(-1);
                }
            } else {
                --k;
                list.remove(list.size() - 1);
            }
        }
        return count;
    }
    private boolean check(List<Integer> list) {
        int an = list.get(list.size() - 1);
        int n = list.size() - 1;
        for (int i = 0; i < list.size() - 1; i++) {
            int ai = list.get(i);
            if (ai == an || Math.abs(ai - an) == Math.abs(i - n))
                return false;
        }
        return true;
    }
    */

    public int totalNQueens(int n) {
        int count = 0;
        int[] array = new int[n];
        array[0] = -1;
        int k = 0;
        while (k >= 0) {
            array[k] = array[k] + 1;
            while (array[k] < n && !check(array, k))
                array[k] = array[k] + 1;
            if (array[k] < n) {
                if (k == n - 1) {
                    ++count;
                } else {
                    ++k;
                    array[k] = -1;
                }
            } else
                --k;
        }
        return count;
    }

    private boolean check(int[] array, int k) {
        int ak = array[k];
        for (int i = 0; i < k; i++) {
            int ai = array[i];
            if (ai == ak || Math.abs(ai - ak) == Math.abs(i - k))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        P52 p52 = new P52();
        System.out.println(p52.totalNQueens(8));
    }

}
