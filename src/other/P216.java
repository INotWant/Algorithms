package other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iwant
 * @date 19-5-25 17:06
 */
public class P216 {

// 思路有问题
//    public List<List<Integer>> combinationSum3(int k, int n) {
//        List<List<Integer>> result = new ArrayList<>();
//        int sum1 = 0;
//        int sum2 = 0;
//
//        for (int i = 1; i <= k; i++) {
//            sum1 += i;
//            sum2 += (10 - i);
//        }
//        if (n < sum1 || n > sum2)
//            return result;
//
//        List<Integer> base = new ArrayList<>();
//        for (int i = 0; i < k; i++)
//            base.add(0);
//
//        int count = 0;
//
//        Set<String> set = new HashSet<>();
//
//        for (; ; ++count) {
//            sum1 = 0;
//            for (int j = 1; j <= k; j++) {
//                base.set(j - 1, count + j);
//                sum1 += count + j;
//            }
//            if (sum1 > n)
//                break;
//            int i = k - 1;
//            int m = 9;
//            while (sum1 != n) {
//                int d = n - sum1;
//                if (d <= m - base.get(i)) {
//                    base.set(i, d + base.get(i));
//                    sum1 = n;
//                } else {
//                    sum1 += (m - base.get(i));
//                    base.set(i, m);
//                }
//                --i;
//                --m;
//            }
//            helper(base, k - 1, set);
//        }
//        for (String resultStr : set) {
//            resultStr = resultStr.substring(1, resultStr.length() - 1);
//            ArrayList<Integer> list = new ArrayList<>();
//            String[] split = resultStr.split(", ");
//            for (String str : split)
//                list.add(Integer.parseInt(str));
//            result.add(list);
//        }
//        return result;
//    }
//
//
//    private void helper(List<Integer> base, int pos, Set<String> set) {
//        if (pos > 0) {
//            int pre = base.get(pos - 1);
//            int curr = base.get(pos);
//            int savePre = pre;
//            int saveCurr = curr;
//            while (pre < curr) {
//                base.set(pos - 1, pre);
//                base.set(pos, curr);
//                if (pos == 1)
//                    set.add(base.toString());
//                helper(base, pos - 1, set);
//                --curr;
//                ++pre;
//            }
//            base.set(pos - 1, savePre);
//            base.set(pos, saveCurr);
//        }
//    }


    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        helper(res, n, k, list, 1);
        return res;
    }

    public void helper(List<List<Integer>> res, int tar, int k, List<Integer> temp, int start) {
        if (k < 0) return;
        if (tar == 0 && k == 0)
            res.add(new ArrayList<>(temp));

        for (int i = start; i <= 9; i++) {
            if (i > tar) return;
            temp.add(i);
            helper(res, tar - i, k - 1, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        P216 p216 = new P216();
        int k = 4;
        int n = 24;
        System.out.println(p216.combinationSum3(k, n));
    }

}
