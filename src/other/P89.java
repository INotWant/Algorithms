package other;

import java.util.ArrayList;
import java.util.List;

public class P89 {

    /*
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            result.add(0);
            return result;
        }
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < n; i++)
            numStr.append("0");
        for (int i = 1; i <= n; i++)
            result.addAll(helper(numStr, i));
        return result;
    }

    private List<Integer> helper(StringBuilder numStr, int pos) {
        List<Integer> result = new ArrayList<>();
        if (pos == 1) {
            result.add(Integer.parseInt(numStr.toString(), 2));
            if (numStr.charAt(numStr.length() - 1) == '0')
                numStr.setCharAt(numStr.length() - 1, '1');
            else
                numStr.setCharAt(numStr.length() - 1, '0');
            result.add(Integer.parseInt(numStr.toString(), 2));
        } else {
            int p = numStr.length() - pos;
            if (numStr.charAt(p) == '0')
                numStr.setCharAt(p, '1');
            else
                numStr.setCharAt(p, '0');
            for (int i = 1; i < pos; i++)
                result.addAll(helper(numStr, i));
        }
        return result;
    }
    // */

    // 方法二: 使用公式 G(n) = B(n) ^ ( B(n) >> 1 )
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            res.add(i ^ i >> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        P89 p89 = new P89();
        System.out.println(p89.grayCode(0));
    }

}
