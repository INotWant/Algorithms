package other;

public class P777 {

    // 主要考虑性质的发现
    // "LX"替换一个"XL"
    // "XR"替换一个"RX"

    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) {
            return false;
        }

        int i = 0;
        int j = 0;
        while (i < start.length() && j < end.length()) {
            while (i < start.length() && start.charAt(i) == 'X') {
                ++i;
            }

            while (j < end.length() && end.charAt(j) == 'X') {
                ++j;
            }

            if (i < start.length() && j < end.length()){
                char c1 = start.charAt(i);
                char c2 = end.charAt(j);
                if (c1 != c2) {
                    return false;
                } else {
                    if (c1 == 'R' && i > j) {
                        return false;
                    } else if (c2 == 'L' && i < j) {
                        return false;
                    }
                }
                ++i;
                ++j;
            }
        }

        while (i < start.length()) {
            if (start.charAt(i++) != 'X') {
                return false;
            }
        }
        while (j < end.length()) {
            if (end.charAt(j++) != 'X') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P777 p777 = new P777();

        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";

//        String start = "X";
//        String end = "L";
        System.out.println(p777.canTransform(start, end));
    }

}
