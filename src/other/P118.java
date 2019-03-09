package other;

import java.util.ArrayList;
import java.util.List;

public class P118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list;
        if (numRows > 0) {
            list = new ArrayList<>();
            list.add(1);
            result.add(list);
        }
        for (int i = 2; i <= numRows; i++) {
            List<Integer> lastList = result.get(i - 2);
            list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i - 1; j++) {
                list.add(lastList.get(j - 1) + lastList.get(j));
            }
            list.add(1);
            result.add(list);
        }
        return result;
    }

}
