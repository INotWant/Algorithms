package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.length() == 0 || endWord.length() == 0 || wordList.size() == 0) {
            return 0;
        }
        int step = 0;
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        while (!set.contains(endWord)) {
            Set<String> tSet = new HashSet<>();
            for (String word : set) {
                for (int i = wordList.size() - 1; i >= 0; i--) {
                    if (isOneDiff(wordList.get(i), word)) {
                        tSet.add(wordList.get(i));
                        wordList.remove(i);
                    }
                }
            }
            if (0 == tSet.size()) {
                return 0;
            }
            set = tSet;
            ++step;
        }
        return step + 1;
    }

    private boolean isOneDiff(String strA, String strB) {
        int count = 0;
        for (int i = 0; i < strA.length(); i++) {
            if (strA.charAt(i) != strB.charAt(i)) {
                ++count;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        P127 p127 = new P127();
        System.out.println(p127.ladderLength(beginWord, endWord, wordList));
    }

}
