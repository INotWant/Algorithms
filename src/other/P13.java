package other;

import java.util.HashMap;
import java.util.Map;

/**
 * url :: https://leetcode.com/problems/roman-to-integer/description/
 *
 * @author kissx on 2018/2/7.
 */
public class P13 {

    private static final Map<Character, Integer> m1 = new HashMap<>();
    private static final Map<Character, Integer> m2 = new HashMap<>();

    static {
        m1.put('I', 0);
        m1.put('V', 1);
        m1.put('X', 2);
        m1.put('L', 3);
        m1.put('C', 4);
        m1.put('D', 5);
        m1.put('M', 6);

        m2.put('I', 1);
        m2.put('V', 5);
        m2.put('X', 10);
        m2.put('L', 50);
        m2.put('C', 100);
        m2.put('D', 500);
        m2.put('M', 1000);
    }

    public static int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char cc = s.charAt(i);
            char lc = 'I';
            if (i + 1 < s.length())
                lc = s.charAt(i + 1);
            if (m1.get(cc) < m1.get(lc))
                result -= m2.get(cc);
            else
                result += m2.get(cc);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("XXI"));
    }

}
