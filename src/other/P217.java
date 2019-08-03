package other;

import java.util.HashSet;
import java.util.Set;

/**
 * @author iwant
 * @date 19-5-25 18:45
 */
public class P217 {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n))
                return true;
            set.add(n);
        }
        return false;
    }

}
