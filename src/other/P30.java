package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url :: https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * @author kissx on 2018/2/18.
 */
public class P30 {

    public List<Integer> findSubstring(String s, String[] words) {
        if (words == null || s == null || s.length() == 0 || words.length == 0)
            return null;
        List<Integer> resultList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            Integer count = map.get(word);
            count = count == null ? 1 : count + 1;
            map.put(word, count);
        }
        int wordNum = words[0].length();
        int lengthSubstring = wordNum * words.length;
        for (int i = 0; i < s.length() - lengthSubstring + 1; i++) {
            Map<String, Integer> wMap = new HashMap<>();
            wMap.putAll(map);
            String str = s.substring(i, i + lengthSubstring);
            boolean flag = true;
            for (int j = 0; j < str.length(); ) {
                String temp = str.substring(j, j + wordNum);
                Integer count = wMap.get(temp);
                if (count == null || count <= 0) {
                    flag = false;
                    break;
                } else
                    wMap.put(temp, --count);
                j += wordNum;
            }
            if (flag)
                resultList.add(i);
        }
        return resultList;
    }

}
