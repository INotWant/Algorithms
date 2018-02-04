package other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * url :: https://leetcode.com/problems/regular-expression-matching/description/
 *
 * @author kissx on 2018/2/4.
 */
public class P10 {

    public static boolean isMatch(String s, String p) {
        return Pattern.compile(p).matcher(s).matches();
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("c*a*b");
        Matcher m = pattern.matcher("cab");
        System.out.println(m.matches());
    }

}
