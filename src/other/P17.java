package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url :: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * @author kissx on 2018/2/9.
 */
public class P17 {
    private static Map<Character, String> map = new HashMap<>();

    static {
        map.put('1', "");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0)
            return result;
        helper(digits, 0, result);
        return result;
    }

    private static void helper(String digits, int pos, List<String> result) {
        char c = digits.charAt(pos);
        String str = map.get(c);
        if (pos == digits.length() - 1) {
            for (int i = 0; i < str.length(); i++)
                result.add(String.valueOf(str.charAt(i)));
        } else {
            helper(digits, pos + 1, result);
            if (!"".equals(str)) {
                List<String> tList = new ArrayList<>();
                if (result.size() > 0) {
                    for (int i = 0; i < str.length(); i++) {
                        for (String aResult : result)
                            tList.add(str.charAt(i) + aResult);
                    }
                } else
                    for (int i = 0; i < str.length(); i++)
                        tList.add(String.valueOf(str.charAt(i)));
                result.clear();
                result.addAll(tList);
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(letterCombinations("12131"));
        System.out.println(letterCombinations("1"));
        System.out.println(letterCombinations(""));
    }

}
