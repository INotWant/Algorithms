package other;

import java.util.Stack;

public class P211 {

}

class WordDictionary {

    private class Node {
        Node[] nodes = new Node[26];
        boolean flag = false;
    }

    private Node head = new Node();

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        if (word == null || "".equals(word))
            return;
        Node node = head;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node newNode = node.nodes[c - 'a'];
            if (newNode == null) {
                newNode = new Node();
                node.nodes[c - 'a'] = newNode;
            }
            if (i == word.length() - 1)
                newNode.flag = true;
            node = newNode;
        }
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    // 相当于 BFS --> 超时
    /*
    public boolean search(String word) {
        if (word == null || "".equals(word))
            return false;
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(head);
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            List<Node> tempNodeList = new ArrayList<>();
            if ('.' == c) {
                if (i == word.length() - 1) {
                    for (Node node1 : nodeList)
                        for (Node node2 : node1.nodes)
                            if (node2 != null && node2.flag)
                                return true;
                    return false;
                }
                for (Node node1 : nodeList)
                    for (Node node2 : node1.nodes)
                        if (node2 != null)
                            tempNodeList.add(node2);
                nodeList = tempNodeList;
            } else {
                if (i == word.length() - 1) {
                    for (Node node1 : nodeList)
                        if (node1.nodes[c - 'a'] != null && node1.nodes[c - 'a'].flag)
                            return true;
                    return false;
                }
                for (Node node1 : nodeList)
                    if (node1.nodes[c - 'a'] != null)
                        tempNodeList.add(node1.nodes[c - 'a']);
                nodeList = tempNodeList;
            }
        }
        return false;
    }
    // */

    // TODO 想不明白这里使用此为什么会超时
    // 回溯 相当于 DFS --> 超时
    public boolean search(String word) {
        if (word == null || "".equals(word))
            return false;
        int count = 0;
        int[] poss = new int[word.length()];
        Node node = head;
        Stack<Node> stack = new Stack<>();
        for (; ; ) {
            char c = word.charAt(count);
            int pos;
            if (c == '.') {
                if (poss[count] == 26)
                    pos = -1;
                else
                    pos = poss[count]++;
            } else {
                if (poss[count] == 26)
                    pos = -1;
                else {
                    pos = c - 'a';
                    poss[count] = 26;
                }
            }
            if (pos != -1) {
                if (count == word.length() - 1) {
                    if (node.nodes[pos] != null && node.nodes[pos].flag)
                        return true;
                } else if (node.nodes[pos] != null) {
                    stack.add(node);
                    node = node.nodes[pos];
                    ++count;
                    continue;
                }
            }
            if (count == 0 && poss[0] == 26)
                return false;
            if (poss[count] == 26) {
                for (int i = count; i < word.length(); i++)
                    poss[i] = 0;
                node = stack.pop();
                --count;
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("ran");
        wordDictionary.addWord("rune");
        wordDictionary.addWord("runner");
        wordDictionary.addWord("runs");
        wordDictionary.addWord("add");
        wordDictionary.addWord("adds");
        wordDictionary.addWord("adder");
        wordDictionary.addWord("addee");

        System.out.println(wordDictionary.search("r.n"));
        System.out.println(wordDictionary.search("ru.n.e"));
        System.out.println(wordDictionary.search("add"));
        System.out.println(wordDictionary.search("add."));
        System.out.println(wordDictionary.search("adde."));
        System.out.println(wordDictionary.search(".an."));
        System.out.println(wordDictionary.search("...s"));
        System.out.println(wordDictionary.search("....e."));
        System.out.println(wordDictionary.search("......."));
        System.out.println(wordDictionary.search("..n.r"));
    }

}


//class WordDictionary {
//
//    private class TrieNode {
//        public int begin;
//        public int end;
//        public TrieNode[] nexts;
//
//        public TrieNode() {
//            begin = 0;
//            end = 0;
//            nexts = new TrieNode[26];
//        }
//    }
//
//    private TrieNode root;
//
//    /**
//     * Initialize your data structure here.
//     */
//    public WordDictionary() {
//        root = new TrieNode();
//    }
//
//    /**
//     * Adds a word into the data structure.
//     */
//    public void addWord(String word) {
//        if (word == null) return;
//        TrieNode node = root;
//        char[] chs = word.toCharArray();
//        for (int i = 0; i < chs.length; i++) {
//            int index = chs[i] - 'a';
//            if (node.nexts[index] == null)
//                node.nexts[index] = new TrieNode();
//            node = node.nexts[index];
//            node.begin++;
//        }
//        node.end++;
//    }
//
//    /**
//     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
//     */
//    public boolean search(String word) {
//        if (word == null) return false;
//        return searchHelper(word.toCharArray(), root, 0);
//    }
//
//    // 相当于 DFS
//    private boolean searchHelper(char[] word, TrieNode root, int k) {
//        if (k == word.length)
//            return root.end > 0;
//        boolean exist = false;
//        if (word[k] == '.') {
//            for (int i = 0; i < 26; i++) {
//                if (root.nexts[i] == null)
//                    continue;
//                exist |= searchHelper(word, root.nexts[i], k + 1);
//            }
//        } else {
//            int i = word[k] - 'a';
//            if (root.nexts[i] == null)
//                return false;
//            exist = searchHelper(word, root.nexts[i], k + 1);
//        }
//        return exist;
//    }
//
//}