package other;

import java.util.HashMap;
import java.util.Map;

public class P115 {

    private int helper(String s, String t, Map<String, Integer> saveMap) {
        int totalNum = 0;
        if (null == s || null == t) {
            return totalNum;
        }
        if (t.length() > s.length() || 0 == s.length() || 0 == t.length()) {
            return totalNum;
        }
        String key = s.length() + "," + t.length();
        Integer history = saveMap.get(key);
        if (null == history) {
            if (1 == t.length()) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == t.charAt(0)) {
                        ++totalNum;
                    }
                }
                return totalNum;
            }
            int sPos = s.length() - 1;
            int tPos = t.length() - 1;
            if (s.charAt(sPos) == t.charAt(tPos)) {
                totalNum = helper(s.substring(0, sPos), t, saveMap);
                totalNum += helper(s.substring(0, sPos), t.substring(0, tPos), saveMap);
            } else {
                totalNum = helper(s.substring(0, sPos), t, saveMap);
            }
            saveMap.put(key, totalNum);
        } else {
            totalNum = history;
        }
        return totalNum;
    }

    public int numDistinct(String s, String t) {
        Map<String, Integer> saveMap = new HashMap<>();
        return helper(s, t, saveMap);
    }

    public static void main(String[] args) {
        String s = "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc";
        String t = "bcddceeeebecbc";

//        String s = "rabbbit";
//        String t = "rabbit";

        P115 p115 = new P115();
        System.out.println(p115.numDistinct(s, t));
    }

}
