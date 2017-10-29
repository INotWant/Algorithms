package chapter16;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 贪心算法 ---- 活动选择问题
 *
 * @author kissx on 2017/10/29.
 */
public class ActivitySelector {

    /**
     * @param s 活动开始时间集合
     * @param f 活动结束时间集合
     * @return 被选择的活动集合（标号从 1 开始）
     */
    public Integer[] activitySelector(int[] s, int[] f) {
        if (s.length != f.length)
            return null;
        int[] tArray = new int[f.length];   // 用作标号
        for (int i = 0; i < tArray.length; i++) {
            tArray[i] = i + 1;
        }
        List<Integer> results = new ArrayList<>();
        // 根据结束时间，对 s，f 两个数组进行排序
        for (int i = 0; i < f.length; i++) {
            int min = f[i];
            int t = i;
            for (int j = i + 1; j < f.length; j++) {
                if (min > f[j]) {
                    min = f[j];
                    t = j;
                }
            }
            if (t != i) {
                f[t] = f[i];
                f[i] = min;
                int temp = s[i];
                s[i] = s[t];
                s[t] = temp;
                temp = tArray[i];
                tArray[i] = tArray[t];
                tArray[t] = temp;
            }
        }
        results.add(tArray[0]);
        int fEnd = f[0];
        for (int i = 1; i < f.length; i++) {
            if (s[i] >= fEnd) {
                results.add(tArray[i]);
                fEnd = f[i];
            }
        }
        Integer[] resultArray = new Integer[results.size()];
        results.toArray(resultArray);
        return resultArray;
    }

//    ------------------ TEST ------------------

    @Test
    public void test() {
        int[] s = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] f = {4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
        Integer[] results = activitySelector(s, f);
        System.out.println("最大兼容活动集合为：" + Arrays.toString(results));
    }

}
