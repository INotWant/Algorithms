package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _baidu_1 {

    // http://bestcoder.hdu.edu.cn/contests/contest_showproblem.php?cid=826&pid=1006

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][2];
        List[] lists = new List[n];

        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            arr[i][0] = x;
            arr[i][1] = y;

            int tn = Integer.parseInt(split[2]);
            lists[i] = new ArrayList<Integer>();
            for (int j = 0; j < tn; j++) {
                line = scanner.nextLine();
                split = line.split(" ");
                x = Integer.parseInt(split[0]);
                y = Integer.parseInt(split[1]);
                lists[i].add(x);
                lists[i].add(y);
            }
        }

        for (int i = 0; i < n; i++) {
            int result = help(arr[i][0], arr[i][1], lists[i]);
            System.out.println(result);
        }

    }

    private static int help(int x, int y, List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i += 2) {
            int tx = list.get(i);
            int ty = list.get(i + 1);

            int min = Math.min(tx, ty);
            min = Math.min(min, y - ty);
            min = Math.min(min, x - tx);
            sum += min;
        }
        return sum;
    }

}
