package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1239 {

    private static int maxLen = 0;

    public int maxLength(List<String> arr) {
        maxLen = 0;
        char[][] chars = new char[arr.size()][];
        for (int i = 0; i < arr.size(); i++) {
            chars[i] = arr.get(i).toCharArray();
        }
        boolean[] visited = new boolean[arr.size()];
        help(chars, 0, visited, new HashSet<>());
        return maxLen;
    }

    public void help(char[][] chars, int index, boolean[] visited, Set<Character> midStr) {
        for (int i = index; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            char[] arr = chars[i];
            visited[i] = true;

            int j = 0;
            for (; j < arr.length; j++) {
                char c = arr[j];
                if (!midStr.contains(c)) {
                    midStr.add(c);
                } else {
                    break;
                }
            }

            if (j == arr.length) {
                if (midStr.size() > maxLen) {
                    maxLen = midStr.size();
                }
                help(chars, i + 1, visited, midStr);
                for (int k = 0; k < j; k++) {
                    midStr.remove(arr[k]);
                }
            } else {
                for (int k = 0; k < j; k++) {
                    midStr.remove(arr[k]);
                }
            }
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        P1239 p1239 = new P1239();
        List<String> list = new ArrayList<>();
        list.add("yy");
        list.add("bkhwmpbiisbldzknpm");
        System.out.println(p1239.maxLength(list));
    }
}
