package other;

public class _leetcode_4_4 {

    public int longestDecomposition(String text) {
        if (text == null || text.length() == 0)
            return 0;
        int i = 0, j = text.length() - 1;
        int s = 0, e = text.length() - 1;
        int count = 0;
        while (i < j) {
            if (text.substring(s, i + 1).equals(text.substring(j, e + 1))) {
                s = i + 1;
                e = j - 1;
                ++i;
                --j;
                ++count;
            } else {
                ++i;
                --j;
            }
        }
        count *= 2;
        if (i == j || s < e)
            ++count;
        return count;
    }

    public static void main(String[] args) {
        _leetcode_4_4 obj = new _leetcode_4_4();
        System.out.println(obj.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
        System.out.println(obj.longestDecomposition("merchant"));
        System.out.println(obj.longestDecomposition("antaprezatepzapreanta"));
        System.out.println(obj.longestDecomposition("aaaa"));
        System.out.println(obj.longestDecomposition("aaa"));
    }

}
