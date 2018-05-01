package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P60 {

    public String getPermutation(int n, int k) {
        Map<Integer, Integer> saveMap = new HashMap<>();
        List<Character> tempList = new ArrayList<>();
        int mul = 1;
        for (int i = 1; i <= n; i++) {
            mul *= i;
            saveMap.put(i, mul);
            tempList.add((char) (i + '0'));
        }
        StringBuilder result = new StringBuilder();
        int count = n;
        while (result.length() != n - 1) {
            int i;
            if (k % saveMap.get(count - 1) == 0) {
                i = k / saveMap.get(count - 1) - 1;
                k -= i * saveMap.get(count - 1);
            } else {
                i = k / saveMap.get(count - 1);
                k = k % saveMap.get(count - 1);
            }
            result.append(tempList.get(i));
            tempList.remove(i);
            --count;
        }
        result.append(tempList.get(0));
        return result.toString();
    }

    public static void main(String[] args) {
        P60 p61 = new P60();
        System.out.println(p61.getPermutation(4, 9));
    }

}
