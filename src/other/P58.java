package other;

public class P58 {

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
        P58 p60 = new P58();
        System.out.println(p60.lengthOfLastWord("a "));
        System.out.println(p60.lengthOfLastWord("gao  "));
        System.out.println(p60.lengthOfLastWord("gao ting"));
    }

}
