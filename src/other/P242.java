package other;

public class P242 {

    public boolean isAnagram(String s, String t) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            ++arr1[c - 'a'];
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            ++arr2[c - 'a'];
        }

        for (int i = 0; i < 26; i++)
            if (arr1[i] != arr2[i])
                return false;

        return true;
    }

}
