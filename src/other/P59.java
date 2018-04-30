package other;

public class P59 {

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int lastCount = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (' ' == c) {
                if (count != 0)
                    lastCount = count;
                count = 0;
            } else
                ++count;
        }
        if (count == 0)
            return lastCount;
        return count;
    }

    public static void main(String[] args) {
        P59 p59 = new P59();
        System.out.println(p59.lengthOfLastWord("a "));
        System.out.println(p59.lengthOfLastWord("gao  "));
        System.out.println(p59.lengthOfLastWord("gao ting"));
    }

}
