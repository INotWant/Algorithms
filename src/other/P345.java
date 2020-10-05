package other;

public class P345 {

    public static String reverseVowels(String s) {
        if (null == s || s.length() == 0)
            return s;
        char[] charArray = s.toCharArray();
        // a e i o u
        int i = 0, j = charArray.length - 1;
        while (i < j) {
            while (i < j && !isYuanYin(charArray[i]))
                ++i;
            if (i >= j)
                break;
            while (i < j && !isYuanYin(charArray[j]))
                --j;
            if (i >= j)
                break;
            char t = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = t;
            ++i;
            --j;
        }
        return new String(charArray);
    }

    private static boolean isYuanYin(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

}
