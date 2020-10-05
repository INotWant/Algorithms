package other;

public class P344 {

    public void reverseString(char[] s) {
        char c;
        for (int i = 0; i < s.length / 2; i++) {
            c = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = c;
        }
    }

}
