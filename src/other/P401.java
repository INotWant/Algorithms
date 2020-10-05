package other;

import java.util.ArrayList;
import java.util.List;

public class P401 {

    public List<String> readBinaryWatch(int num) {
        List<String> saveList = new ArrayList<>();
        helper(num, 0, new int[10], saveList);
        return saveList;
    }

    private void helper(int num, int currIndex, int[] states, List<String> saveList) {
        if (num == 0) {
            String str = toStr(states);
            if (str != null)
                saveList.add(str);
        } else if (num > 0){
            if (currIndex >= states.length)
                return;
            states[currIndex] = 1;
            helper(num - 1, currIndex + 1, states, saveList);
            states[currIndex] = 0;
            helper(num, currIndex + 1, states, saveList);
        }
    }

    private String toStr(int[] states) {
        int h = 0;
        h += states[0] * 8 + states[1] * 4 + states[2] * 2 + states[3];
        if (h > 11)
            return null;
        int min = 0;
        min += states[4] * 32 + states[5] * 16 + states[6] * 8 + states[7] * 4 + states[8] * 2 + states[9];
        if (min > 59)
            return null;
        return h + ":" + (min < 10 ? "0" + min : String.valueOf(min));
    }

}
