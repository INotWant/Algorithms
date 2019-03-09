package other;

public class P125 {

    public boolean isPalindrome(String s) {
        boolean result = true;
        if (null == s || s.length() == 0) {
            return true;
        }
        char[] sChars = s.toCharArray();
        int startIndex = 0;
        int endIndex = sChars.length - 1;
        while (startIndex < endIndex && result) {
            if (!Character.isLetter(sChars[startIndex]) && !Character.isDigit(sChars[startIndex])) {
                ++startIndex;
                continue;
            }
            if (!Character.isLetter(sChars[endIndex]) && !Character.isDigit(sChars[endIndex])) {
                --endIndex;
                continue;
            }
            if (Character.isDigit(sChars[startIndex])) {
                result = sChars[startIndex] == sChars[endIndex];
            } else {
                result = (sChars[startIndex] + "").compareToIgnoreCase(sChars[endIndex] + "") == 0;
            }
            ++startIndex;
            --endIndex;
        }

        return result;
    }

    public static void main(String[] args) {
        P125 p125 = new P125();
        System.out.println(p125.isPalindrome("race a car"));
    }
}
