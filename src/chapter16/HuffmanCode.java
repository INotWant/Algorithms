package chapter16;

import java.util.*;

/**
 * 贪心算法 ---- HuffmanCode
 * 要点：
 * 1）每次选择两个词频最小的做贪心选择；
 * 2）檀香刑选择生成的结点的 str 记录了 “路径”；
 *
 * @author kissx on 2017/11/5.
 */
public class HuffmanCode {

    private Map<String, String> huffmanCode = new HashMap<>();
    private List<Node> characterList;

    public HuffmanCode(List<Node> characterList) {
        this.characterList = characterList;
    }

    public static class Node {
        public final String str;        // token
        public final Integer number;    // 词频

        public Node(String str, Integer number) {
            this.str = str;
            this.number = number;
        }
    }

    /**
     * 获取哈夫曼编码
     *
     * @return 返回一个 Map 集合，其中 key 对应 token 而 value 对应 相应的编码
     */
    public Map<String, String> getHuffmanCode() {
        // Comparator.comparing(o -> o.number) 等价于 (o1, o2) -> o1.number.compareTo(o2.number)
        huffmanCode.clear();
        TreeSet<Node> characterCollection = new TreeSet<>(Comparator.comparing(o -> o.number));
        characterCollection.addAll(characterList);
        while (characterCollection.size() >= 2) {
            Node n1 = characterCollection.pollFirst();
            Node n2 = characterCollection.pollFirst();
            String nStr = n1.str + "," + n2.str;
            Integer nNumber = n1.number + n2.number;
            characterCollection.add(new Node(nStr, nNumber));
            for (String str : n1.str.split(",")) {
                /* 与下面等价
                String code = huffmanCode.get(str);
                if (code == null)
                    huffmanCode.put(str,"1");
                else
                    huffmanCode.put(str,"1" + code);
                // */
                huffmanCode.merge(str, "1", (a, b) -> b + a);
            }
            for (String str : n2.str.split(",")) {
                huffmanCode.merge(str, "0", (a, b) -> b + a);
            }
        }
        return huffmanCode;
    }

    /**
     * 打印 Huffman 编码结果
     */
    public void print() {
        if (huffmanCode.size() == 0)
            getHuffmanCode();
        System.out.println("[Huffman Code]:");
        for (Map.Entry<String, String> entry : huffmanCode.entrySet()) {
            System.out.println("\t" + entry.getKey() + " :: " + entry.getValue());
        }
    }

    public void setCharacterList(List<Node> characterList) {
        this.characterList = characterList;
    }

    public List<Node> getCharacterList() {
        return characterList;
    }

    //    -------------- TEST --------------

    public static void main(String[] args) {
        ArrayList<Node> characterList = new ArrayList<>();
        characterList.add(new Node("e", 9));
        characterList.add(new Node("f", 5));
        characterList.add(new Node("c", 12));
        characterList.add(new Node("b", 13));
        characterList.add(new Node("a", 45));
        characterList.add(new Node("d", 16));
        HuffmanCode huffmanCode = new HuffmanCode(characterList);
        huffmanCode.getHuffmanCode();
        huffmanCode.print();
    }


}
