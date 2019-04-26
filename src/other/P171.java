package other;

public class P171 {

    public int titleToNumber(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++)
            n += (Math.pow(26, s.length() - 1 - i) * (s.charAt(i) - 'A' + 1));
        return n;
    }

    public static void main(String[] args) {
        P171 p171 = new P171();
        System.out.println(p171.titleToNumber("ZY"));
    }

}
