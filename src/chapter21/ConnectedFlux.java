package chapter21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 连通分量问题
 *
 * @author kissx on 2017/11/18.
 */
public class ConnectedFlux {

    public static class Node {
        public final char a;
        public final char b;

        public Node(char a, char b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void connectedFlux(char[] vArray, Node[] eArray) {
        DisjointSetByList<Character> disjointSetByList = new DisjointSetByList<>();
        for (Character aVArray : vArray) {
            disjointSetByList.makeSet(aVArray);
        }
        for (Node anEArray : eArray) {
            disjointSetByList.union(anEArray.a, anEArray.b);
        }
        Set<String> set = new HashSet<>();
        for (Character aVArray : vArray) {
            Character[] setElement = disjointSetByList.findSetElement(aVArray, Character.class);
            set.add(Arrays.toString(setElement));
        }
        System.out.println(set);
    }

    public static void main(String[] args) {
        char[] vArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        Node[] eArray = new Node[7];
        eArray[0] = new Node('b','d');
        eArray[1] = new Node('e','g');
        eArray[2] = new Node('a','c');
        eArray[3] = new Node('h','i');
        eArray[4] = new Node('a','b');
        eArray[5] = new Node('e','f');
        eArray[6] = new Node('b','c');
        connectedFlux(vArray,eArray);
    }

}
