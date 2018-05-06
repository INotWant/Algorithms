package other;

public class P72 {

    /*
    // 第一次尝试把 “编辑距离” 问题转为 “LCS” 问题，然而思路错误
    public int minDistance(String word1, String word2) {
        char[][] saveArray = new char[word1.length()][word2.length()];
        int size = helper(word1, word2, word1.length() - 1, word2.length() - 1, saveArray);
        for (char[] array : saveArray) {
            System.out.println(Arrays.toString(array));
        }
        StringBuilder w1 = new StringBuilder();
        StringBuilder w2 = new StringBuilder();
        int i = word1.length() - 1;
        int j = word2.length() - 1;
        while (size != 0) {
            if (saveArray[i][j] == '\\') {
                --i;
                --j;
                --size;
                w1.insert(0, 1);
                w2.insert(0, 1);
            } else if (saveArray[i][j] == '|') {
                --i;
                w1.insert(0, 0);
            } else if (saveArray[i][j] == '-') {
                --j;
                w2.insert(0, 0);
            }
        }
        while (w1.length() != word1.length())
            w1.insert(0, 0);
        while (w2.length() != word2.length())
            w2.insert(0, 0);

        return 0;

    }

    private int helper(String word1, String word2, int i, int j, char[][] saveArray) {
        int num;
        if (i == 0 && j == 0) {
            if (word1.charAt(0) == word2.charAt(0)) {
                num = 1;
                saveArray[0][0] = '\\';
            } else
                num = 0;
        } else if (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                num = helper(word1, word2, i - 1, j - 1, saveArray) + 1;
                saveArray[i][j] = '\\';
            } else {
                int n1 = helper(word1, word2, i, j - 1, saveArray);
                int n2 = helper(word1, word2, i - 1, j, saveArray);
                num = n1 > n2 ? n1 : n2;
                if (num == n1)
                    saveArray[i][j] = '-';
                else
                    saveArray[i][j] = '|';
            }
        } else
            num = 0;
        return num;
    }
    // */

    // 第二次尝试：类似 LCS 解法
    public int minDistance(String word1, String word2) {
        if ("".equals(word1))
            return word2.length();
        if ("".equals(word2))
            return word1.length();
        int[][] saveArray = new int[word1.length()][word2.length()];
        return helper(word1, word2, word1.length() - 1, word2.length() - 1, saveArray);
    }

    private static int helper(String word1, String word2, int i, int j, int[][] saveArray) {
        if (i >= 0 && j >= 0 && saveArray[i][j] != 0)
            return saveArray[i][j];
        int num;
        if (i == 0 && j == 0) {
            if (word1.charAt(0) == word2.charAt(0))
                num = 0;
            else
                num = 1;
        } else if (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                num = helper(word1, word2, i - 1, j - 1, saveArray);
            } else {
                int n1 = helper(word1, word2, i, j - 1, saveArray) + 1;
                int n2 = helper(word1, word2, i - 1, j, saveArray) + 1;
                int n3 = helper(word1, word2, i - 1, j - 1, saveArray) + 1;
                num = n1 > n2 ? (n2 > n3 ? n3 : n2) : (n1 > n3 ? n3 : n1);
            }
        } else {
            num = 1;
            if (i + 1 > num)
                num = i + 1;
            if (j + 1 > num)
                num = j + 1;
        }
        if (i >= 0 && j >= 0)
            saveArray[i][j] = num;
        return num;
    }

    public static void main(String[] args) {
//        String word1 = "intention";
//        String word2 = "execution";

        String word1 = "dinitrophenylhydrazine";
        String word2 = "benzalphenylhydrazone";
        P72 p72 = new P72();
        System.out.println(p72.minDistance(word1, word2));
    }

}
