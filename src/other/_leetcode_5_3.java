package other;

import java.util.ArrayList;
import java.util.List;

public class _leetcode_5_3 {

    @SuppressWarnings("unchecked")
    public int maxRepOpt1(String text) {
        List[] lists = new List[26];
        for (int i = 0; i < lists.length; i++)
            lists[i] = new ArrayList();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int len = 1;
            int j = i + 1;
            while (j < text.length() && c == text.charAt(j)) {
                ++len;
                ++j;
            }
            lists[c - 'a'].add(i);
            lists[c - 'a'].add(len);
            i = j - 1;
        }
        int max = 1;
        for (List value : lists) {
            int currMax = 0;
            if (value.size() == 0)
                continue;
            else if (value.size() == 2) {
                currMax = (int) value.get(1);
            } else if (value.size() == 4) {
                int t1 = (int) value.get(0);
                int n1 = (int) value.get(1);
                int t2 = (int) value.get(2);
                int n2 = (int) value.get(3);
                if (t1 + n1 + 1 == t2)
                    currMax = n1 + n2;
                else
                    currMax = Math.max(n1, n2) + 1;
            } else {
                for (int i = 0; i < value.size() - 2; ) {
                    int t1 = (int) value.get(i);
                    int n1 = (int) value.get(i + 1);
                    int t2 = (int) value.get(i + 2);
                    int n2 = (int) value.get(i + 3);
                    if (t1 + n1 + 1 == t2) {
                        if (n1 + n2 + 1 > currMax)
                            currMax = n1 + n2 + 1;
                    } else if (Math.max(n1, n2) + 1 > currMax)
                        currMax = Math.max(n1, n2) + 1;
                    i += 2;
                }
            }
            if (currMax > max)
                max = currMax;
        }
        return max;
    }
}
