package other;

public class P67 {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        while (b.length() != a.length())
            b = "0" + b;
        boolean is = false;
        for (int i = a.length() - 1; i >= 0; i--) {
            int aNum = a.charAt(i) - '0';
            int bNum = b.charAt(i) - '0';
            int cNum;
            if (is)
                cNum = aNum + bNum + 1;
            else
                cNum = aNum + bNum;
            if (cNum == 2) {
                sb.insert(0, 0);
                is = true;
            } else if (cNum == 3) {
                sb.insert(0, 1);
                is = true;
            } else {
                sb.insert(0, cNum);
                is = false;
            }
        }
        if (is)
            sb.insert(0, 1);
        return sb.toString();
    }

}
