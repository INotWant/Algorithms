package other;

import java.util.ArrayList;
import java.util.List;

public class _leetcode_8_1 {

    public static List<String> buildArray(int[] target, int n) {
        List<String> result = new ArrayList<>();
        int j = 0;
        for (int curr : target) {
            ++j;
            while (curr != j) {
                result.add("Push");
                result.add("Pop");
                ++j;
            }
            result.add("Push");
        }
        return result;
    }

    public static void main(String[] args) {
        int[] target = {2,3,4};
        int n = 4;
        System.out.println(buildArray(target, n));
    }

}
