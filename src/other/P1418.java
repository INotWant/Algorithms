package other;

import java.util.*;

public class P1418 {

    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> dSet = new HashSet<>();
        Set<Integer> tIdSet = new HashSet<>();
        Map<String, Map<String, Integer>> save = new HashMap<>();
        for (List<String> order : orders) {
            String tId = order.get(1);
            String d = order.get(2);
            tIdSet.add(Integer.parseInt(tId));
            dSet.add(d);
            save.putIfAbsent(tId, new HashMap<>());
            Map<String, Integer> tMap = save.get(tId);
            tMap.putIfAbsent(d, 0);
            tMap.put(d, tMap.get(d) + 1);
        }
        String[] dStrs = new String[dSet.size()];
        dSet.toArray(dStrs);
        Arrays.sort(dStrs);
        Integer[] tIdStrs = new Integer[tIdSet.size()];
        tIdSet.toArray(tIdStrs);
        Arrays.sort(tIdStrs);
        List<List<String>> result = new LinkedList<>();
        List<String> one = new LinkedList<>();
        one.add("Table");
        one.addAll(Arrays.asList(dStrs));
        result.add(one);
        for (Integer tId : tIdStrs) {
            Map<String, Integer> tMap = save.get(String.valueOf(tId));
            List<String> oList = new LinkedList<>();
            oList.add(String.valueOf(tId));
            for (String d : dStrs) {
                Integer num = tMap.get(d);
                if (num == null)
                    num = 0;
                oList.add(String.valueOf(num));
            }
            result.add(oList);
        }
        return result;
    }

}
