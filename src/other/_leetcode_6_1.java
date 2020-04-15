package other;

public class _leetcode_6_1 {

    public int countCharacters(String[] words, String chars) {
        int[] as = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            int c = chars.charAt(i);
            ++as[c - 'a'];
        }
        int sum = 0;
        for (String word : words) {
            boolean flag = true;
            int[] tas = new int[26];
            System.arraycopy(as, 0, tas, 0, 26);
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (tas[c - 'a'] == 0) {
                    flag = false;
                    break;
                } else
                    --tas[c - 'a'];
            }
            if (flag)
                sum += word.length();
        }
        return sum;
    }

}
