package other;

public class P1417 {

    public String reformat(String s) {
        int len = s.length();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c))
                sb1.append(c);
            else
                sb2.append(c);
        }
        if (len % 2 == 0 && sb1.length() != sb2.length())
            return "";
        if (len % 2 != 0 && Math.abs(sb1.length() - sb2.length()) != 1)
            return "";
        StringBuilder result = new StringBuilder();
        String s1 = sb1.toString();
        String s2 = sb2.toString();
        int i = 0;
        if (s1.length() > s2.length()) {
            while (i < s2.length()) {
                result.append(s1.charAt(i));
                result.append(s2.charAt(i));
                ++i;
            }
            result.append(s1.charAt(i));
        } else if (s1.length() < s2.length()) {
            while (i < s1.length()) {
                result.append(s2.charAt(i));
                result.append(s1.charAt(i));
                ++i;
            }
            result.append(s2.charAt(i));
        } else {
            while (i < s1.length()) {
                result.append(s1.charAt(i));
                result.append(s2.charAt(i));
                ++i;
            }
        }
        return result.toString();
    }

}
