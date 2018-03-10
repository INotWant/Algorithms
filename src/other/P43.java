/**
 * url :: https://leetcode.com/problems/multiply-strings/description/
 * 他人思路：两个数中的第 i 位与第 j 位相乘所得的落入的“坑”是固定的！
 * 
 * @author kissx on 2018/3/10.
 */
public class P43 {

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2))
            return "0";
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1; i >= 0; i--) {
            StringBuilder tempSb = new StringBuilder();
            int a = num1.charAt(i) - '0';
            int n = 0;
//            if (a == 0)
//                continue;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                tempSb.insert(0, (char) ((a * b + n) % 10 + '0'));
                n = (a * b + n) / 10;
            }
            if (n != 0)
                tempSb.insert(0, (char) (n + '0'));
            n = 0;
            int num = num1.length() - 1 - i;
            int count = num == 0 ? -1 : sb.length() - num - 1;
            for (int j = tempSb.length() - 1; j >= 0; j--) {
                a = tempSb.charAt(j) - '0';
                if (count >= 0) {
                    int b = sb.charAt(count--) - '0';
                    sb.replace(count + 1, count + 2, String.valueOf((a + b + n) % 10));
                    n = (a + b + n) / 10;
                } else {
                    if (n != 0) {
                        sb.insert(0, (char) ((a + n) % 10 + '0'));
                        n = (a + n) / 10;
                    } else
                        sb.insert(0, (char) (a + '0'));
                }
            }
            if (n != 0)
                sb.insert(0, (char) (n + '0'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        P43 p43 = new P43();
        System.out.println(p43.multiply("0", "314"));
        System.out.println(p43.multiply("123", "314"));
//        System.out.println(p43.multiply("123", "456"));
//        System.out.println(p43.multiply("123456789", "987654321"));
//        System.out.println(p43.multiply("140", "721"));
    }

}
