package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author kissx on 2018/3/16.
 */
public class _360_P1 {

    public static void main(String[] args) {
        int m, n;
        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split(" ");
        m = Integer.parseInt(split[0]);
        n = Integer.parseInt(split[1]);
        int[] mArray = new int[m];
        String mStr = scanner.nextLine();
        String[] mSplit = mStr.split(" ");
        int count = 0;
        for (String str : mSplit)
            mArray[count++] = Integer.parseInt(str);
        int[] nArray = new int[n];
        count = 0;
        while (count < n)
            nArray[count++] = Integer.parseInt(scanner.nextLine());
        Arrays.sort(mArray);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(mArray[mArray.length - 1], mArray[mArray.length - 1] + 1);
        for (int i = mArray.length - 2; i >= 0; i--) {
            if (mArray[i + 1] - mArray[i] == 1)
                map.put(mArray[i], map.get(mArray[i + 1]));
            else
                map.put(mArray[i], mArray[i] + 1);
        }
        for (int i = 0; i < nArray.length; i++) {
            Integer integer = map.get(nArray[i]);
            if (integer == null)
                System.out.println(nArray[i]);
            else
                System.out.println(integer);
        }
    }
}
