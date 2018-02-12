package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url :: https://leetcode.com/problems/generate-parentheses/description/
 *
 * @author kissx on 2018/2/12.
 */
public class P22 {

    public List<String> generateParenthesis(int n) {
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("()");
        map.put(1, list);
        for (int i = 2; i <= n; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int p = i - 1 - j;
                if (j == 0) {
                    String str = "()";
                    List<String> tList = map.get(p);
                    for (String s : tList)
                        list.add(str + s);
                } else {
                    List<String> tList1 = map.get(j);
                    List<String> tList2 = map.get(p);
                    StringBuilder sb = new StringBuilder();
                    for (String s1 : tList1) {
                        sb.append("(").append(s1).append(")");
                        if (tList2 != null) {
                            for (String s2 : tList2)
                                list.add(sb.toString() + s2);
                        } else
                            list.add(sb.toString());
                        sb.delete(0, sb.length());
                    }
                }
            }
            map.put(i, list);
        }
        return map.get(n);
    }

    public static void main(String[] args) {

        P22 p22 = new P22();
        System.out.println(p22.generateParenthesis(3));
        System.out.println(p22.generateParenthesis(4));
    }
}
