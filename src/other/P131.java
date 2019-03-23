package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P131 {

    private boolean isHuiWen(String str) {
        int p = 0, q = str.length() - 1;
        while (p < q) {
            if (str.charAt(p) != str.charAt(q))
                return false;
            ++p;
            --q;
        }
        return true;
    }

    // 1）方法一
    /*
    private void helper(String s, int start, int end, String midStr, Map<String, Boolean> saveMap, List<List<String>> result) {
        if (end < start)
            return;
        for (int i = start; i <= end; i++) {
            String sub = s.substring(start, i + 1);
            if (i + 1 == s.length() && isHuiWen(sub)) {
                List<String> list = new ArrayList<>();
                for (String str : (midStr + "," + sub).split(",")) {
                    if (str.length() > 0)
                        list.add(str);
                }
                result.add(list);
            }
            if (null == saveMap.get(sub)) {
                if (isHuiWen(sub)) {
                    saveMap.put(sub, true);
                    helper(s, i + 1, end, midStr + "," + sub, saveMap, result);
                } else {
                    saveMap.put(sub, false);
                }
            } else if (saveMap.get(sub)) {
                helper(s, i + 1, end, midStr + "," + sub, saveMap, result);
            }
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (null == s || 0 == s.length())
            return result;
        Map<String, Boolean> saveMap = new HashMap<>();
        helper(s, 0, s.length() - 1, "", saveMap, result);
        return result;
    }
    // */

    // 方法二 -- 改进了中间结果的保存方式
    private void helper(String s, int left, Map<String, Boolean> saveMap, List<String> mResult, List<List<String>> result) {
        if (left == s.length()) {
            result.add(new ArrayList<>(mResult));
            return;
        }
        for (int i = left; i < s.length(); i++) {
            String sub = s.substring(left, i + 1);
            if (null == saveMap.get(sub)) {
                if (isHuiWen(sub)) {
                    saveMap.put(sub, true);
                    mResult.add(sub);
                    helper(s, i + 1, saveMap, mResult, result);
                    mResult.remove(mResult.size() - 1);
                }
            } else if (saveMap.get(sub)) {
                mResult.add(sub);
                helper(s, i + 1, saveMap, mResult, result);
                mResult.remove(mResult.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (null == s || 0 == s.length())
            return result;
        Map<String, Boolean> saveMap = new HashMap<>();
        helper(s, 0, saveMap, new ArrayList<>(), result);
        return result;
    }

}
