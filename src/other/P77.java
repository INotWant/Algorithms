package other;

import java.util.ArrayList;
import java.util.List;

public class P77 {

    /*
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int size = (int) Math.pow(2, n);
        for (int i = 1; i < size; i++) {
            int num = i;
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if ((num & 1) == 1) {
                    list.add(j);
                }
                num >>= 1;
            }
            if (list.size() == k)
                result.add(list);
        }
        return result;
    }
    */

    private void combine(int n, int k, int start, List<Integer> record, List<List<Integer>> list) {
        if (record.size() == k) {
            list.add(new ArrayList<>(record));
            return;
        }
        // record.size() + (n - i + 1)) >= k 保证 “剩余的够用”
        for (int i = start; i <= n && (record.size() + (n - i + 1)) >= k; i++) {
            record.add(i);
            combine(n, k, i + 1, record, list);
            record.remove(record.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> record = new ArrayList<>();
        if (k <= 0 || n <= 0 || k > n) {
            return list;
        }
        combine(n, k, 1, record, list);
        return list;
    }

    public static void main(String[] args) {
        P77 p77 = new P77();
        System.out.println(p77.combine(4, 2));
    }

}
