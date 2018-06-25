package other;

public class P91 {

    public int numDecodings(String s) {
        if (s == null || "".equals(s))
            return 0;
        if ('0' == s.charAt(0))
            return 0;
        if (s.length() == 1)
            return 1;
        int[] array = new int[s.length()];
        array[0] = 1;
        if (Integer.parseInt(s.substring(0, 2)) <= 26 && s.charAt(1) != '0') {
            if (s.length() >= 3 && s.charAt(2) == '0')
                array[1] = 1;
            else
                array[1] = 2;
        } else if (s.charAt(1) == '0' && s.charAt(0) > '2')
            return 0;
        else
            array[1] = 1;
        for (int i = 2; i < s.length(); i++) {
            if ((i < s.length() - 1 && s.charAt(i + 1) == '0')) {
                if (s.charAt(i) > '2')
                    return 0;
                array[i] = array[i - 1];
            } else if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2')
                    return 0;
                array[i] = array[i - 1];
            } else {
                int num = Integer.parseInt(s.substring(i - 1, i + 1));
                if (num <= 26 && s.charAt(i - 1) != '0')
                    array[i] = array[i - 1] + array[i - 2];
                else
                    array[i] = array[i - 1];
            }
        }
        return array[s.length() - 1];
    }

    public static void main(String[] args) {
        P91 p91 = new P91();
        System.out.println(p91.numDecodings("301"));
    }

}
