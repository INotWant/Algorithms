package other;

/**
 * @author kissx on 2018/3/5.
 */
public class P38 {

    public String countAndSay(int n) {
        int count = 1;
        String cStr = "1";
        while (n != count) {
            StringBuilder sb = new StringBuilder();
            int number = 1;
            char c = cStr.charAt(0);
            for (int i = 1; i < cStr.length(); i++) {
                if (c == cStr.charAt(i))
                    ++number;
                else {
                    sb.append(number).append(c);
                    number = 1;
                    c = cStr.charAt(i);
                }
            }
            sb.append(number).append(c);
            cStr = sb.toString();
            ++count;
        }
        return cStr;
    }

    public static void main(String[] args) {
        P38 p38 = new P38();
        System.out.println(p38.countAndSay(6));
        System.out.println(p38.countAndSay(5));
        System.out.println(p38.countAndSay(4));
        System.out.println(p38.countAndSay(3));
    }
}
