package other;

import java.util.HashSet;
import java.util.Set;

public class P202 {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        for (; ; ) {
            String str = String.valueOf(n);
            n = 0;
            for (int i = 0; i < str.length(); i++)
                n += Math.pow(str.charAt(i) - '0', 2);
            if (n == 1)
                return true;
            if (set.contains(n))
                return false;
            set.add(n);
        }
    }
}
