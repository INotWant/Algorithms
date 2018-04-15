package other;

import java.util.*;

public class P49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String keyStr = String.valueOf(chars);
            List<String> list = map.get(keyStr);
            if (list == null) {
                list = new ArrayList<>();
                map.put(keyStr, list);
            }
            list.add(str);
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet())
            result.add(entry.getValue());
        return result;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        P49 p49 = new P49();
        List<List<String>> result = p49.groupAnagrams(strs);
        System.out.println(result);
    }

}
