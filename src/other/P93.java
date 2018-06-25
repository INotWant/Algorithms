package other;

import java.util.ArrayList;
import java.util.List;

public class P93 {

    private void help(String s, int start, int count, String tStr, List<String> result) {
        if (count == 1) {
            if (start < s.length() && s.length() - start <= 3) {
                int num = Integer.parseInt(s.substring(start, s.length()));
                if (num <= 255) {
                    if ((tStr + num).length() == s.length() + 3)
                        result.add(tStr + num);
                }
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (start > s.length() || start + i > s.length())
                break;
            String subStr = s.substring(start, start + i);
            int num = Integer.parseInt(subStr);
            if (num > 255)
                break;
            if (count == 4)
                help(s, start + i, count - 1, String.valueOf(num) + ".", result);
            else
                help(s, start + i, count - 1, tStr + num + ".", result);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        help(s, 0, 4, "", result);
        return result;
    }

    public static void main(String[] args) {
        P93 p93 = new P93();
        System.out.println(p93.restoreIpAddresses("010010"));
    }

}
