package other;

public class P97 {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 != null && s2 != null && s3 != null) {
            if (s1.length() == s3.length() && s2.length() == 0)
                return s1.equals(s3);
            if (s2.length() == s3.length() && s1.length() == 0)
                return s2.equals(s3);
            if (s1.length() + s2.length() != s3.length())
                return false;
            int s1p = 0;
            int s2p = 0;
            int s3p = 0;
            boolean flag = false;
            while (s1p <= s1.length() && s2p < s2.length()) {
                if (s1p == s1.length()) {
                    if (s2.charAt(s2p) == s3.charAt(s3p)) {
                        ++s2p;
                        ++s3p;
                    } else {
                        --s1p;
                        --s3p;
                        flag = true;
                    }
                } else {
                    if (s1p < 0)
                        return false;
                    else {
                        // 优先考虑 s1 的
                        if (!flag && s1.charAt(s1p) == s3.charAt(s3p)) {
                            ++s1p;
                            ++s3p;
                        } else if (s2.charAt(s2p) == s3.charAt(s3p)) {
                            ++s2p;
                            ++s3p;
                            flag = false;
                        } else {
                            --s1p;  // 回溯
                            --s3p;
                            flag = true;
                        }
                    }
                }
            }
            if (s3p == s3.length())
                return true;
            else
                return s3.substring(s3p).equals(s1.substring(s1p));
        } else {
            if (s1 == null && s2 == null && s3 == null)
                return true;
            else
                return false;
        }
    }

    public static void main(String[] args) {
        P97 p97 = new P97();

        String s2 = "aabcc";
        String s1 = "dbbca";
        String s3 = "aadbbcbcac";

//        String s1 = "aabcc";
//        String s2 = "dbbca";
//        String s3 = "aadbbbaccc";

        System.out.println(p97.isInterleave(s1, s2, s3));
    }

}
