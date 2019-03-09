package other;

import java.util.*;

public class P126 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        boolean isGo = true;
        while (isGo) {
            Set<String> tSet = new HashSet<>();
            Set<Integer> deleteSet = new HashSet<>();
            for (String word : set) {
                int index = word.lastIndexOf(",") >= 0 ? word.lastIndexOf(",") + 1 : 0;
                String currWord = word.substring(index);
                if (currWord.equals(endWord)) {
                    List<String> list = Arrays.asList(word.split(","));
                    result.add(list);
                    isGo = false;
                    continue;
                }
                for (int i = wordList.size() - 1; i >= 0; i--) {
                    if (isOneDiff(wordList.get(i), currWord)) {
                        tSet.add(word + "," + wordList.get(i));
                        if (!endWord.equals(wordList.get(i))) {
                            deleteSet.add(i);
                        }
                    }
                }
            }
            List<Integer> deleteList = new ArrayList<>(deleteSet);
            deleteList.sort(Integer::compareTo);
            for (int i = deleteList.size() - 1; i >= 0; i--) {
                wordList.remove((int) deleteList.get(i));
            }
            if (0 == tSet.size()) {
                isGo = false;
            }
            set = tSet;
        }
        return result;
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

        String beginWord = "ta";
        String endWord = "if";
        List<String> wordList = new ArrayList<>();
        wordList.add("ts");
        wordList.add("sc");
        wordList.add("ph");
        wordList.add("ca");
        wordList.add("jr");
        wordList.add("hf");
        wordList.add("to");
        wordList.add("if");
        wordList.add("ha");
        wordList.add("is");
        wordList.add("io");
        wordList.add("cf");
        wordList.add("ta");

        P126 p126 = new P126();
        System.out.println(p126.findLadders(beginWord, endWord, wordList));
    }

}
