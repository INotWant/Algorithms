package other;

public class P208 {


}

//
//class Trie {
//
//    private static class Node {
//        Map<Character, Node> map = new HashMap<>();
//        boolean flag;
//    }
//
//    private Map<Character, Node> head;
//
//    /**
//     * Initialize your data structure here.
//     */
//    public Trie() {
//        this.head = new HashMap<>();
//    }
//
//    /**
//     * Inserts a word into the trie.
//     */
//    public void insert(String word) {
//        Map<Character, Node> map = this.head;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            Node node = map.get(c);
//            if (node == null) {
//                node = new Node();
//                map.put(c, node);
//            }
//            if (i == word.length() - 1)
//                node.flag = true;
//            map = node.map;
//        }
//    }
//
//    /**
//     * Returns if the word is in the trie.
//     */
//    public boolean search(String word) {
//        Map<Character, Node> map = this.head;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            Node node = map.get(c);
//            if (node == null)
//                return false;
//            if (i == word.length() - 1 && !node.flag)
//                return false;
//            map = node.map;
//        }
//        return true;
//    }
//
//    /**
//     * Returns if there is any word in the trie that starts with the given prefix.
//     */
//    public boolean startsWith(String prefix) {
//        Map<Character, Node> map = this.head;
//        for (int i = 0; i < prefix.length(); i++) {
//            char c = prefix.charAt(i);
//            Node node = map.get(c);
//            if (node == null)
//                return false;
//            map = node.map;
//        }
//        return true;
//    }
//
//}
//

class Trie {

    private static class Node {
        Node[] nodes = new Node[26];
        boolean flag;
    }

    private Node[] head = new Node[26];

    /**
     * Initialize your data structure here.
     */
    public Trie() {
    }

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
