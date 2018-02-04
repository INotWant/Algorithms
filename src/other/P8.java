package other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url :: https://leetcode.com/problems/string-to-integer-atoi/description/
 *
 * @author kissx on 2018/2/4.
 */
public class P8 {

    public static int myAtoi(String str) {
        Matcher matcher = Pattern.compile("[-+]?[0-9]+").matcher(str);
        if (matcher.find()) {
            // 查看匹配的前面是否包含 非空白字符，若有则返回 0
            int start = matcher.start();
            if (Pattern.compile("[^\\s]+").matcher(str.substring(0, start)).find())
                return 0;
            str = matcher.group();
        } else
            return 0;
        try {
            long l = Long.parseLong(str);
            if (l < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            else if (l > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            return (int) l;
        } catch (Exception e) {
            if ('-' == str.charAt(0))
                return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("2147483648"));
        System.out.println(myAtoi("    +1"));
        System.out.println(myAtoi("9223372036854775809"));
        System.out.println(myAtoi("-0012a42"));
        System.out.println(myAtoi("   - 321"));
        System.out.println(myAtoi(" b11228552307"));
        System.out.println(myAtoi(" 9223372036854775809"));
    }

}
