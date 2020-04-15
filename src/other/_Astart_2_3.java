package other;

import java.util.*;

public class _Astart_2_3 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int c = 0;

        int[] results = new int[count];

        while (c < count) {
            String str = scanner.next();
            List<Integer> countList = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '?') {
                    int s = i;
                    while (i < str.length() && str.charAt(i) == '?')
                        ++i;
                    countList.add(i - s);
                    --i;
                }
            }
            Set<Integer> set = new HashSet<>();
            for (Integer num : countList) {
                ++num;
                int t = 2;
                while (t <= num)
                    t *= 2;
                t /= 2;
                while (t >= 1) {
                    if (num - t >= 0 && !set.contains(t)) {
                        if (t != 1)
                            set.add(t);
                        num -= t;
                    }
                    t /= 2;
                }
            }
            int sum = 0;
            for (int num : set)
                sum += num;
            if (sum > 0)
                sum += (str.length() - sum + 1) % 2;
            else
                sum = (str.length() + 1) % 2;
//            if ((str.length() + 1) % 2 == 1)
//                ++sum;
            results[c] = sum;
            ++c;
        }
        for (int result : results)
            System.out.println(result);
    }
}
