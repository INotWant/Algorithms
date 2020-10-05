package other;

import java.util.Arrays;

public class Sunday {

    /**
     * 思路：考虑 str 上指针怎么跳
     */
    public static boolean sunday(String str, String template) {
        int[] table = buildTable(template);
        int i = 0, j = 0;
        int tmp;
        while (i < str.length()) {
            tmp = i;
            while (j < template.length() && i < str.length() && str.charAt(i) == template.charAt(j)) {
                ++i;
                ++j;
            }
            if (j == template.length())
                return true;
            if (tmp + template.length() >= str.length())
                return false;
            i = tmp + table[str.charAt(tmp + template.length()) - 'a'];
            j = 0;
        }
        return false;
    }

    private static int[] buildTable(String template) {
        int[] alphs = new int[26];
        Arrays.fill(alphs, template.length() + 1);
        for (int i = 0; i < template.length(); i++) {
            int index = template.charAt(i) - 'a';
            alphs[index] = template.length() - i;
        }
        return alphs;
    }

    public static void main(String[] args) {
        String str = "ababbc";
        System.out.println(sunday(str, "a"));
        System.out.println(sunday(str, "ab"));
        System.out.println(sunday(str, "aba"));
        System.out.println(sunday(str, "cabab"));
    }

}
