package other;

import java.util.ArrayList;
import java.util.List;

public class _leetcode_1_1 {

    @SuppressWarnings("unchecked")
    public int problem(String source, String target) {
        List[] lists = new List[26];
        for (int i = 0; i < lists.length; i++)
            lists[i] = new ArrayList();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            lists[c - 'a'].add(i);
        }
        int result = 0;
        int lastPos = -1;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            List list = lists[c - 'a'];
            if (list.size() == 0)
                return -1;
            boolean flag = false;
            for (Object obj : list) {
                int pos = (int) obj;
                if (pos > lastPos) {
                    lastPos = pos;
                    flag = true;
                    break;
                }
            }
            if (!flag)
                ++result;
        }
        return result == 0 ? ++result : result;
    }

    public static void main(String[] args) {
        _leetcode_1_1 obj = new _leetcode_1_1();
        System.out.println(obj.problem("abc", "abcbc"));
        System.out.println(obj.problem("abc", "ab"));
        System.out.println(obj.problem("abc", "acdbc"));
        System.out.println(obj.problem("xyz", "xzyxz"));
    }


}
