package other;

import java.util.ArrayList;
import java.util.List;

public class P164 {

    // 桶排序
    @SuppressWarnings("unchecked")
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1)
            return 0;
        List[] lists1 = new List[10];
        List[] lists2 = new List[10];
        for (int i = 0; i < 10; i++) {
            lists1[i] = new ArrayList();
            lists2[i] = new ArrayList();
        }
        int div = 10;
        for (int num : nums)
            lists1[num % div].add(num);
        boolean flag = true;
        int i = 1;
        for (; i < 10 && flag; i++, div *= 10) {
            flag = false;
            if (i % 2 == 1) {
                for (List list : lists1) {
                    for (Object obj : list) {
                        int num = (int) obj;
                        if (num > 0) {
                            flag = true;
                            lists2[num / div % 10].add(num);
                        }
                    }
                    list.clear();
                }
            } else {
                for (List list : lists2) {
                    for (Object obj : list) {
                        int num = (int) obj;
                        if (num > 0) {
                            flag = true;
                            lists1[num / div % 10].add(num);
                        }
                    }
                    list.clear();
                }
            }
        }
        if (i % 2 == 0)
            lists1 = lists2;
        int last = -1, result = 0;
        for (List list : lists1)
            for (Object obj : list) {
                int num = (int) obj;
                if (last >= 0)
                    result = num - last > result ? num - last : result;
                last = num;
            }
        return result;
    }

}
