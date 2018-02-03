package other;

/**
 * url :: https://leetcode.com/problems/zigzag-conversion/description/
 *
 * @author kissx on 2018/2/3.
 */
public class P6 {

    // zigzag pattern ???
    //*
    public static String convert(String s, int numRows) {
        if ("".equals(s) || numRows == 1)
            return s;
        int t = numRows - 1;
        int colmn = (s.length() / (2 * numRows - 2) + 1) * t;
        char[][] chars = new char[numRows][colmn];
        int count = 0;
        for (int i = 0; count < s.length(); i++) {
            if (i % t == 0) {
                for (int j = 0; j < numRows && count < s.length(); j++)
                    chars[j][i] = s.charAt(count++);
            } else
                chars[numRows - (i % t) - 1][i] = s.charAt(count++);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < colmn; j++)
                if (chars[i][j] != '\u0000')
                    sb.append(chars[i][j]);
        return sb.toString();
    }
    // */

    public static void main(String[] args) {
        System.out.println(convert("ABCD", 2));
        if (convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"))
            System.out.println("OK!!!");
    }
}
