package other;

public class P264 {

    // 方法二
    /*
    public int nthUglyNumber(int n) {
        if (n == 1)
            return 1;

        TreeSet<Long> set = new TreeSet<>();
        set.add(1L);

        int index = 1;
        long higher = 0;
        while (index < n) {
            long t = set.higher(higher);
            if (t * 2 > 0)
                set.add(t * 2);
            if (t * 3 > 0)
                set.add(t * 3);
            if (t * 5 > 0)
                set.add(t * 5);

            higher = t;
            ++index;
        }

        return Math.toIntExact(set.higher(higher));
    }
    */

    // 方法一：超时
    /*
    public int nthUglyNumber(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int count = 2, curr = 2;
        List<Integer> zhiShuList = new ArrayList<>();
        zhiShuList.add(2);

        while (true) {
            label1:
            while (true) {
                ++curr;
                int last = zhiShuList.get(zhiShuList.size() - 1);
                if (curr > last) {
                    last = getNextZhiShu(zhiShuList);
                    zhiShuList.add(last);
                    System.out.println(zhiShuList.size());
                }
                for (int i = 3; i < zhiShuList.size(); i++)
                    if (curr % zhiShuList.get(i) == 0)
                        continue label1;
                break;
            }
            ++count;
            if (count == n)
                return curr;
        }
    }

    private int getNextZhiShu(List<Integer> zhiShuList) {
        int len = zhiShuList.size();
        int curr = zhiShuList.get(len - 1) + 1;
        label:
        while (true) {
            for (Integer zhiShu : zhiShuList)
                if (curr % zhiShu == 0) {
                    ++curr;
                    continue label;
                }
            break;
        }
        return curr;
    }
     */

    // 方法三
    // 该方法的主要思想：x2 x3 x5 相互交替的过程（每个都维护已经乘到哪）
    public int nthUglyNumber(int n) {
        int[] arr = new int[1690];

        int count = 1;
        arr[0] = 1;
        int r2 = 0, r3 = 0, r5 = 0;

        while (count != n) {
            int temp = Math.min(arr[r2] * 2, Math.min(arr[r3] * 3, arr[r5] * 5));
            arr[++count - 1] = temp;
            if (arr[r2] * 2 == temp) ++r2;
            if (arr[r3] * 3 == temp) ++r3;
            if (arr[r5] * 5 == temp) ++r5;
        }
        return arr[n - 1];
    }


    public static void main(String[] args) {
        P264 p264 = new P264();
        for (int i = 1; i < 1690; i++)
            System.out.println(p264.nthUglyNumber(i));

//        System.out.println(p264.nthUglyNumber(1600));


    }

}
