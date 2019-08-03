package other;

import java.util.*;

/**
 * @author iwant
 * @date 19-5-18 14:15
 */
public class P212 {

    private static class Trie {

        private static class Node {
            Node[] nodes = new Node[26];
            boolean flag;
        }

        private Node[] head = new Node[26];

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node[] nodes = head;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node node = nodes[c - 'a'];
                if (node == null) {
                    node = new Node();
                    nodes[c - 'a'] = node;
                }
                if (i == word.length() - 1)
                    node.flag = true;
                nodes = node.nodes;
            }
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node[] nodes = head;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node node = nodes[c - 'a'];
                if (node == null)
                    return false;
                if (i == word.length() - 1 && !node.flag)
                    return false;
                nodes = node.nodes;
            }
            return true;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node[] nodes = head;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                Node node = nodes[c - 'a'];
                if (node == null)
                    return false;
                nodes = node.nodes;
            }
            return true;
        }
    }

    /*
     * 解题思路： DFS + 前缀树
     */
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> set = new HashSet<>();
        if (board == null || board.length == 0 || words == null || words.length == 0)
            return new ArrayList<>(set);
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++) {
                for (boolean[] booleans : isVisited)
                    Arrays.fill(booleans, false);
                helper(trie, isVisited, board, i, j, "", set);
            }
        return new ArrayList<>(set);
    }

    private void helper(Trie trie, boolean[][] isVisited, char[][] board, int i, int j, String midStr, Set<String> result) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length)
            return;
        if (!isVisited[i][j]) {
            isVisited[i][j] = true;
            midStr += board[i][j];
            if (!trie.startsWith(midStr)) {
                // 注意
                isVisited[i][j] = false;
                return;
            }
            if (trie.search(midStr))
                result.add(midStr);
            helper(trie, isVisited, board, i - 1, j, midStr, result);
            helper(trie, isVisited, board, i, j - 1, midStr, result);
            helper(trie, isVisited, board, i + 1, j, midStr, result);
            helper(trie, isVisited, board, i, j + 1, midStr, result);
            // 注意
            isVisited[i][j] = false;
        }
    }

    public static void main(String[] args) {
        char[][] boards = {{'a', 'b'}, {'c', 'd'}};
        String[] words = {"cdba"};

        P212 p212 = new P212();
        System.out.println(p212.findWords(boards, words));
    }

}



