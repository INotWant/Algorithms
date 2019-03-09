package other;

import java.util.List;

public class P120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (null == triangle || 0 == triangle.size()) {
            return 0;
        }
        for (int i = 1; i < triangle.size(); i++) {
            int len = i + 1;
            List<Integer> lastList = triangle.get(i - 1);
            List<Integer> currList = triangle.get(i);
            currList.set(0, currList.get(0) + lastList.get(0));
            for (int j = 1; j < len - 1; j++) {
                int min = Math.min(lastList.get(j - 1), lastList.get(j));
                currList.set(j, currList.get(j) + min);
            }
            currList.set(len - 1, currList.get(len - 1) + lastList.get(len - 2));
        }
        int result = Integer.MAX_VALUE;
        for (int n : triangle.get(triangle.size() - 1)) {
            if (result > n) {
                result = n;
            }
        }
        return result;
    }

}
