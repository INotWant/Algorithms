package other;

import java.util.Scanner;

public class _Astart_1_5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int c = 1;

        long[] results = new long[count];

        while (c <= count) {
            long n = scanner.nextLong();
            if (n == 1)
                results[c - 1] = 1;
            else if (n == 2)
                results[c - 1] = 1;
            else if (n == 3)
                results[c - 1] = 0;
            else if (n == 4)
                results[c - 1] = 3;
            else if (n == 5)
                results[c - 1] = 0;
            else {
                long a = (n - 5) / 6;
                long b = (n - 5) % 6;
                if (b == 0)
                    results[c - 1] = a;
                else if (b == 1)
                    results[c - 1] = 3 * (a + 1);
                else if (b == 3)
                    results[c - 1] = 3 * a + 4;
                else if (b == 4)
                    results[c - 1] = a + 1;
                else if (b == 5)
                    results[c - 1] = 6 * a + 9;
                else
                    results[c - 1] = 4 * a + 5;
            }
            ++c;
        }

        for (long result : results)
            System.out.println(result);
    }

}
