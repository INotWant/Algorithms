package other;

import java.util.ArrayList;
import java.util.List;

public class P51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        int k = 0;
        while (k >= 0) {
            list.set(k, list.get(k) + 1);
            while (list.get(k) < n && !check(list))
                list.set(k, list.get(k) + 1);
            if (list.get(k) < n) {
                if (k == n - 1) {
                    result.add(output(list));
                } else {
                    ++k;
                    list.add(-1);
                }
            } else {
                --k;
                list.remove(list.size() - 1);
            }
        }
        return result;
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

    private List<String> output(List<Integer> list) {
        int n = list.size();
        List<String> rList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int pos = list.get(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == pos)
                    sb.append('Q');
                else
                    sb.append('.');
            }
            rList.add(sb.toString());
        }
        return rList;
    }

    public static void main(String[] args) {
        P51 p51 = new P51();
        List<List<String>> result = p51.solveNQueens(8);
        for (int i = 0; i < result.size(); i++) {
            List<String> tList = result.get(i);
            for (int j = 0; j < tList.size(); j++)
                System.out.println(tList.get(j));
            System.out.println();
        }
        System.out.println(result.size());
    }

}
