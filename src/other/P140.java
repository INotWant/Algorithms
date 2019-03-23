package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P140 {

    @SuppressWarnings("unchecked")
    public List<String> wordBreak(String s, List<String> wordDict) {
        int maxWordLen = 0;
        Set<String> wordSet = new HashSet<>();
        for (String word : wordDict) {
            if (word.length() > maxWordLen) {
                maxWordLen = word.length();
            }
            wordSet.add(word);
        }
        List[] lists = new List[s.length() + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList();
        }
        lists[0].add(0);
        for (int i = 1; i <= s.length(); i++) {
            int j = i - maxWordLen >= 0 ? i - maxWordLen : 0;
            for (; j < i; ++j) {
                if (lists[j].size() > 0 && wordSet.contains(s.substring(j, i))) {
                    lists[i].add(j);
                }
            }
        }
        List<String> result = new ArrayList<>();
        constructResult(s, lists, "", s.length(), result);
        return result;
    }

    private void constructResult(String s, List[] lists, String mid, int pos, List<String> result) {
        if (0 == pos) {
            result.add(mid);
            return;
        }
        for (Object objInt : lists[pos]) {
            StringBuilder midBuilder = new StringBuilder(mid);
            int tPos = (int) objInt;
            if (pos == s.length()) {
                midBuilder.insert(0, s.substring(tPos, pos));
            } else {
                midBuilder.insert(0, s.substring(tPos, pos) + " ");
            }
            constructResult(s, lists, midBuilder.toString(), tPos, result);
        }
    }
}
