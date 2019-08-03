package other;

public class _leetcode_3_2 {

    private static final String[] BOARD =
            {
                    "abcde",
                    "fghij",
                    "klmno",
                    "pqrst",
                    "uvwxy",
                    "z"
            };

    private String charPath(char a, char b) {
        int xa = (a - 'a') / 5;
        int ya = (a - 'a') % 5;

        int xb = (b - 'a') / 5;
        int yb = (b - 'a') % 5;

        StringBuilder sb = new StringBuilder();

        if (b != 'z') {
            if (xa - xb < 0)
                for (int i = 0; i < Math.abs(xa - xb); i++) {
                    sb.append("D");
                }
            else if (xa - xb > 0)
                for (int i = 0; i < Math.abs(xa - xb); i++) {
                    sb.append("U");
                }

            if (ya - yb < 0)
                for (int i = 0; i < Math.abs(ya - yb); i++) {
                    sb.append("R");
                }
            else if (ya - yb > 0)
                for (int i = 0; i < Math.abs(ya - yb); i++) {
                    sb.append("L");
                }
        } else {
            if (ya - yb < 0)
                for (int i = 0; i < Math.abs(ya - yb); i++) {
                    sb.append("R");
                }
            else if (ya - yb > 0)
                for (int i = 0; i < Math.abs(ya - yb); i++) {
                    sb.append("L");
                }
            if (xa - xb < 0)
                for (int i = 0; i < Math.abs(xa - xb); i++) {
                    sb.append("D");
                }
            else if (xa - xb > 0)
                for (int i = 0; i < Math.abs(xa - xb); i++) {
                    sb.append("U");
                }
        }
        sb.append("!");
        return sb.toString();
    }

    public String alphabetBoardPath(String target) {
        if (target == null || target.length() == 0)
            return "";
        char curr = 'a';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            sb.append(charPath(curr, target.charAt(i)));
            curr = target.charAt(i);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        _leetcode_3_2 obj = new _leetcode_3_2();
        System.out.println(obj.alphabetBoardPath("zdz"));
    }

}
