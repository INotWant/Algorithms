package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _leetcode_2_2 {

    public String smallestEquivalentString(String A, String B, String S) {
        List<Set<Character>> list = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            char ac = A.charAt(i);
            char bc = B.charAt(i);
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                Set<Character> set = list.get(j);
                if (set.contains(ac) || set.contains(bc)) {
                    set.add(ac);
                    set.add(bc);
                    flag = true;
                    for (int k = j + 1; k < list.size(); k++) {
                        Set<Character> endSet = list.get(k);
                        if (endSet.contains(ac) || endSet.contains(bc)) {
                            set.addAll(endSet);
                            list.remove(k--);
                        }
                    }
                    break;
                }
            }
            if (!flag) {
                Set<Character> set = new HashSet<>();
                set.add(ac);
                set.add(bc);
                list.add(set);
            }
        }

        List<List<Character>> newList = new ArrayList<>();
        for (Set<Character> set : list) {
            List<Character> tList = new ArrayList<>(set);
            tList.sort(Character::compare);
            newList.add(tList);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                Set<Character> set = list.get(j);
                if (set.contains(c)) {
                    flag = true;
                    sb.append(newList.get(j).get(0));
                    break;
                }
            }
            if (!flag)
                sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        String A = "leetcode", B = "programs", S = "sourcecode";
//        String A = "parker", B = "morris", S = "parser";
//        String A = "hello", B = "world", S = "hold";
        String A = "cgokcgerolkgksgbhgmaaealacnsshofjinidiigbjerdnkolc";
        String B = "rjjlkbmnprkslilqmbnlasardrossiogrcboomrbcmgmglsrsj";
        String S = "bxbwjlbdazfejdsaacsjgrlxqhiddwaeguxhqoupicyzfeupcn";

        _leetcode_2_2 obj = new _leetcode_2_2();
        String s = obj.smallestEquivalentString(A, B, S);
        System.out.println(s);
    }
}
