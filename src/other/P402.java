package other;

import java.util.LinkedList;
import java.util.List;

public class P402 {

    //*
    public static String removeKdigits(String num, int k) {
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < num.length(); i++)
            list.add(num.charAt(i));

        int i = 1, count = 0;

        while (i > 0 && i < list.size() && count < k) {
            if (list.get(i - 1) > list.get(i)) {
                list.remove(i - 1);
                ++count;
                if (i != 1)
                    --i;
            } else
                ++i;
        }

        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (char c : list) {
            if (c == '0') {
                if (!flag)
                    sb.append(c);
            } else {
                flag = false;
                sb.append(c);
            }
        }

        String result = sb.toString();
        if (result.length() > num.length() - k)
            result = sb.substring(0, num.length() - k);
        result = result.length() == 0 ? "0" : result;

        return result;
    }
    // */

    public static void main(String[] args) {
        String num = "10";
        int k = 2;
        System.out.println(removeKdigits(num, k));
    }

}
