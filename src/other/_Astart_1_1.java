package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _Astart_1_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int c = 1;

        List<String> result = new ArrayList<>();

        while (c <= count) {
            int len = scanner.nextInt();
            int t;
            int a = 0, n1 = 0;
            for (int i = 0; i < len; i++) {
                if ((t = scanner.nextInt()) != 0) {
                    a = t;
                    n1 = i;
                }
            }
            int b = 0, n2 = 0;
            for (int i = 0; i < len; i++) {
                if ((t = scanner.nextInt()) != 0) {
                    b = t;
                    n2 = i;
                }
            }
            if (n1 > n2)
                result.add("1/0");
            else if (n1 < n2)
                result.add("0/1");
            else {
                int max = help(a, b);
                result.add((a / max) + "/" + (b / max));
            }
            c++;
        }

        for (String s : result)
            System.out.println(s);
    }

    private static int help(int a, int b) {
        int t;
        if (a < b) {
            t = a;
            a = b;
            b = t;
        }
        if (a % b == 0)
            return b;
        t = a % b;
        return help(b, t);
    }

}
