package other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class P179 {

    // AC
    /*
    public String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();
        List<String> numList = new ArrayList<>();
        for (int num : nums)
            numList.add(String.valueOf(num));
        int count = 0;
        while (count < nums.length) {
            String max = numList.get(0);
            int pos = 0;
            for (int i = 1; i < numList.size(); i++) {
                String vStr = numList.get(i);
                if (compare(vStr, max) > 0) {
                    max = vStr;
                    pos = i;
                }
            }
            numList.remove(pos);
            sb.append(max);
            ++count;
        }
        if (sb.charAt(0) == '0')
            sb.delete(1, sb.length());
        return sb.toString();
    }

    private int compare(String vaStr, String vbStr) {
        if (vaStr.equals(vbStr))
            return 0;
        else {
            int len = Math.min(vaStr.length(), vbStr.length());
            for (int i = 0; i < len; i++)
                if (vaStr.charAt(i) > vbStr.charAt(i))
                    return 1;
                else if (vaStr.charAt(i) < vbStr.charAt(i))
                    return -1;
            int i = len;
            int j = 0;
            if (vaStr.length() == len) {
                for (; i < vbStr.length() && j < vaStr.length(); i++, j++)
                    if (vbStr.charAt(i) < vaStr.charAt(j))
                        return 1;
                    else if (vbStr.charAt(i) > vaStr.charAt(j))
                        return -1;
                if (i < vbStr.length() && vbStr.charAt(i) < vaStr.charAt(0))
                    return 1;
                if (j < vaStr.length() && vaStr.charAt(j) > vaStr.charAt(0))
                    return 1;
            } else {
                for (; i < vaStr.length() && j < vbStr.length(); i++, j++)
                    if (vaStr.charAt(i) > vbStr.charAt(j))
                        return 1;
                    else if (vaStr.charAt(i) < vbStr.charAt(j))
                        return -1;
                if (i < vaStr.length() && vaStr.charAt(i) > vbStr.charAt(0))
                    return 1;
                if (j < vbStr.length() && vbStr.charAt(j) < vbStr.charAt(0))
                    return 1;
            }
        }
        return -1;
    }
    // */

    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int number : nums) list.add(Integer.toString(number));
        list.sort((s1, s2) -> {
            String c1 = s1 + s2;
            String c2 = s2 + s1;
            return c2.compareTo(c1);
        });
        return (list.isEmpty() || "0".equals(list.get(0))) ? "0" : String.join("", list);
    }

    public static void main(String[] args) {
        P179 p179 = new P179();
        int[] nums = {3, 30, 34, 5, 9};
//        int[] nums = {35, 353};
//        int[] nums = {353, 35};
//        int[] nums = {0, 0, 0};
//        int[] nums = {20, 1};
        System.out.println(p179.largestNumber(nums));
    }
}
