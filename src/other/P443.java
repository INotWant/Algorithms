package other;

public class P443 {

    public static int compress(char[] chars) {
        if (chars.length == 0)
            return 0;
        int wPos = 0;
        int count = 1;
        char last = chars[0];
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (c == last)
                ++count;
            else {
                if (count == 1)
                    chars[wPos++] = last;
                else {
                    chars[wPos++] = last;
                    String tStr = String.valueOf(count);
                    for (int j = 0; j < tStr.length(); j++)
                        chars[wPos++] = tStr.charAt(j);
                }
                count = 1;
                last = c;
            }
        }
        if (count == 1)
            chars[wPos++] = last;
        else {
            chars[wPos++] = last;
            String tStr = String.valueOf(count);
            for (int j = 0; j < tStr.length(); j++)
                chars[wPos++] = tStr.charAt(j);
        }
        return wPos;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        System.out.println(compress(chars));
        System.out.println(chars);
    }

}
