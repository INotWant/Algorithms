package other;

public class P168 {

    public String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) (n % 26 + 'A'));
            n = n / 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        P168 p168 = new P168();
        System.out.println(p168.convertToTitle(52));

    }

}
