package other;

public class _leetcode_9_4 {

    public static int minimumOneBitOperations(int n) {
        String str = Integer.toBinaryString(n);
        return helper(str);
    }

    private static int helper(String str) {
        if (str.length() == 0)
            return 0;
        int result = 0;
        int i = 0;
        for (; i < str.length(); i++)
            if (str.charAt(i) != '0')
                break;
        if (i < str.length()) {
            if (i == str.length() - 1)
                ++result;
            else if (str.charAt(i + 1) == '0') {
                result += Math.pow(2, str.length() - 1 - i);
                result += helper("1" + str.substring(i + 2));
            } else {
                result += helper(str.substring(i + 2));
                ++result;
                result += (Math.pow(2, str.length() - i - 1) - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minimumOneBitOperations(333));
    }

}
