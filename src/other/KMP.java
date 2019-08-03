package other;

/**
 * @author iwant
 * @date 19-5-26 10:02
 * @desc kmp 算法第一次实现
 */
public class KMP {

    /**
     * @param matchStr    待匹配串
     * @param templateStr 模板串
     * @return -1,表示匹配失败; 大于等于 0 时表示第一次成功匹配模板串的位置
     */
    public static int kmp(String matchStr, String templateStr) {
        if (matchStr == null || matchStr.length() == 0 || templateStr == null || templateStr.length() == 0)
            return -1;

        // 计算失配表
        int[] unmatchTable = new int[templateStr.length()];
        getUnmatchTable(unmatchTable, templateStr);

        // 匹配过程
        int j = 0;  /* templateStr 的指针 */
        for (int i = 0; i < matchStr.length(); i++) {
            if (matchStr.charAt(i) == templateStr.charAt(j)) {
                ++j;
                if (j == templateStr.length())  /* 匹配成功返回 */
                    return i - j + 1;
            } else if (j != 0) {
                j = unmatchTable[j];
                --i;
            }
        }
        return -1;
    }

    /*
     * 计算失配表
     */
    private static void getUnmatchTable(int[] unmatchTable, String templateStr) {
        for (int i = 1; i < unmatchTable.length - 1; ++i) {
            int j = unmatchTable[i];
            while (j != 0 && templateStr.charAt(i) != templateStr.charAt(j))
                j = unmatchTable[j]; //一旦回到1，表明窗口大小为0了，只能回到最初的字符
            unmatchTable[i + 1] = templateStr.charAt(i) == templateStr.charAt(j) ? j + 1 : 0;
        }
    }

    public static void main(String[] args) {
        String matchStr = "aaaabababba";
        String templateStr = "ababba";
        System.out.println(kmp(matchStr, templateStr));
    }


}
