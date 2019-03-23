package other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P139 {
    // dfs 超时
    /*
    @SuppressWarnings("unchecked")
    public boolean wordBreak(String s, List<String> wordDict) {
        ArrayList[] lists = new ArrayList[26];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList();
        }
        for (String word : wordDict) {
            lists[word.charAt(0) - 'a'].add(word);
        }
        return helper(s, 0, lists);
    }

    private boolean helper(String s, int pos, ArrayList[] lists) {
        ArrayList words = lists[s.charAt(pos) - 'a'];
        for (Object objW : words) {
            String word = (String) objW;
            int j = 0;
            for (int i = pos; i < s.length() && j < word.length(); i++) {
                if (s.charAt(i) == word.charAt(j)) {
                    ++j;
                } else {
                    break;
                }
            }
            if (j == word.length()) {
                if (pos + j == s.length() || helper(s, pos + j, lists)) {
                    return true;
                }
            }
        }
        return false;
    }
    // */

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] bp = new boolean[s.length() + 1];
        bp[0] = true;
        Set<String> wordSet = new HashSet<>();
        int maxWordLen = 0;
        for (String word : wordDict) {
            if (word.length() > maxWordLen) {
                maxWordLen = word.length();
            }
            wordSet.add(word);
        }

        for (int i = 1; i <= s.length(); i++) {
            int j = i - maxWordLen >= 0 ? i - maxWordLen : 0;
            for (; j < i; j++) {
                if (bp[j] && wordSet.contains(s.substring(j, i))) {
                    bp[i] = true;
                    break;
                }
            }
        }
        return bp[s.length()];
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        String[] strings = {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"};
        List<String> list = Arrays.asList(strings);
        P139 p139 = new P139();
        p139.wordBreak(s, list);
    }

}
