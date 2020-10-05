package other;

import java.util.Random;

public class P470 {

    private static int rand7() {
        return new Random().nextInt(7) + 1;
    }

    /**
     * 拒绝采样
     */
    public static int rand10() {
        int r;
        do {
            int n1 = rand7();
            int n2 = rand7();
            r = n1 + (n2 - 1) * 7;
        } while (r > 40);
        return ((r - 1) / 4) + 1;
    }
}
