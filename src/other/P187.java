package other;

import java.util.*;

public class P187 {


    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        if (s == null || s.length() == 0)
            return new ArrayList<>(set);
        int[] ints = {1, 0, 2, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4};
        Map<Long, Integer> map = new HashMap<>();
        long temp = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i <= 9) {
                temp += (ints[c - 'A'] * Math.pow(10, i));
                if (i == 9)
                    map.put(temp, 1);
            } else {
                temp /= 10;
                temp += (ints[c - 'A'] * Math.pow(10, 9));
                Integer count = map.get(temp);
                if (count != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = i - 9; j <= i; j++)
                        sb.append(s.charAt(j));
                    set.add(sb.toString());
                } else
                    map.put(temp, 1);
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

    }

}
