package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _Astart_2_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int c = 0;

        List[] results = new List[count];
        for (int i = 0; i < results.length; i++)
            results[i] = new ArrayList();

        while (c < count) {
            int num = scanner.nextInt();
            help(num, results[c]);
            ++c;
        }
        for (List result : results) {
            System.out.println(result.size());
            for (int i = 0; i < result.size(); i++) {
                System.out.print((int) result.get(i));
                if (i != result.size() - 1)
                    System.out.print(" ");
                else
                    System.out.println();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void help(int num, List result) {
        int n = num;
        int sum = 0;
        while (n >= 10) {
            sum += (n % 10);
            n /= 10;
        }
        sum += n;
        for (int i = 1; i <= sum; i++)
            if (sum % i == 0 && num % i == 0)
                result.add(i);
    }
}
