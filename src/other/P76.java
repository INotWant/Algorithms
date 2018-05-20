package other;

public class P76 {

    /* // O(n^k) （k取决于t） TTE
    public String minWindow(String s, String t) {
        if (t == null || t.length() == 0 || s == null || s.length() == 0)
            return "";
        Map<Character, List<Integer>> map = new HashMap<>();
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, new ArrayList<>());
            Integer count = countMap.get(c);
            if (count == null)
                count = 0;
            countMap.put(c, ++count);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.get(c) != null)
                map.get(c).add(i);
        }

        int startPos = -1;
        int endPos = -1;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            Character c = entry.getKey();
            int count = entry.getValue();
            List<Integer> list = map.get(c);
            for (int i = 0; i <= list.size() - count; i++) {
                int pos = list.get(i);
                boolean flag = false;
                int newEndPos = -1;
                for (Map.Entry<Character, Integer> otherEntry : countMap.entrySet()) {
                    Character otherC = otherEntry.getKey();
                    int otherCount = otherEntry.getValue();
                    List<Integer> listTemp = map.get(otherC);
                    for (int p : listTemp) {
                        if (p >= pos)
                            --otherCount;
                        if (otherCount == 0) {
                            if (p > newEndPos)
                                newEndPos = p;
                            break;
                        }
                    }
                    if (otherCount != 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag && (startPos == -1 || newEndPos - pos < endPos - startPos)) {
                    startPos = pos;
                    endPos = newEndPos;
                }
            }
        }
        if (startPos == -1)
            return "";
        return s.substring(startPos, endPos + 1);
    }
    // */

    public String minWindow(String s, String t) {
        if (t == null || t.length() == 0 || s == null || s.length() == 0)
            return "";
        int[] sArray = new int[128];
        int[] tArray = new int[128];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            ++tArray[c];
        }
        int found = 0;
        int pos = 0;
        int start = -1;
        int end = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ++sArray[c];
            if (sArray[c] <= tArray[c])
                ++found;
            if (found == t.length()) {
                for (int j = pos; j < i; j++) {
                    if (sArray[s.charAt(j)] == tArray[s.charAt(j)])
                        break;
                    --sArray[s.charAt(j)];
                    ++pos;
                }
                if (start == -1 || (i - pos) < (end - start)) {
                    start = pos;
                    end = i;
                }
                --sArray[s.charAt(pos++)];
                --found;
            }
        }
        if (end == -1)
            return "";
        return s.substring(start, end + 1);
    }

    public static void main(String[] args) {
        P76 p76 = new P76();
        System.out.println(p76.minWindow("a", "aa"));
        System.out.println(p76.minWindow("aa", "aaa"));
        System.out.println(p76.minWindow("aa", "aa"));
        System.out.println(p76.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(p76.minWindow("a", "ab"));
        System.out.println(p76.minWindow("aab", "aab"));
        System.out.println(p76.minWindow("cabwefgewcwaefgcf", "cae"));
    }

}
