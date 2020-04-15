package other;


import java.util.ArrayList;
import java.util.List;

public class _leetcode_6_4 {

    // TODO

    public String lastSubstring(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        List<Integer> posList = new ArrayList<>();
        char last = s.charAt(0);
        sb.append(last);
        posList.add(0);
        for (int i = 1; i < len; i++) {
            char curr = s.charAt(i);
            if (curr != last) {
                sb.append(curr);
                posList.add(i);
                last = curr;
            }
        }

        String sStr = sb.toString();
        int max = 0;
        len = sStr.length();
        for (int i = 0; i < len; i++) {
            int curr = sStr.charAt(i) - 'a';
            if (curr > max) {
                max = curr;
                if (max == 25)
                    break;
            }
        }
        char c = (char) (max + 'a');
        String result = null;
        int pos = -1;
        for (int i = 0; i < len; i++) {
            if (sStr.charAt(i) == c) {
                if (result == null) {
                    result = sStr.substring(i);
                    pos = i;
                } else {
                    String tmp = sStr.substring(i);
                    boolean flag = false;
                    for (int j = 0; j < tmp.length(); j++) {
                        char ct = tmp.charAt(j);
                        char cr = result.charAt(j);
                        if (ct > cr) {
                            flag = true;
                            break;
                        } else if (ct < cr)
                            break;
                        int tn = getCount(posList, i + j, s.length());
                        int rn = getCount(posList, pos + j, s.length());
                        char ncr = result.charAt(j + 1);
                        if (j != tmp.length() - 1) {
                            char nct = tmp.charAt(j + 1);
                            if (tn != rn) {
                                if ((tn > rn && ncr < ct) || (tn < rn && nct > cr)) {
                                    flag = true;
                                    break;
                                } else
                                    break;
                            }
                        } else {
                            if (tn > rn && ncr < ct) {
                                flag = true;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        result = tmp;
                        pos = i;
                    }
                }
            }
        }
        return s.substring(posList.get(pos));
    }

    private int getCount(List<Integer> posList, int pos, int total) {
        if (pos == posList.size() - 1)
            return total - posList.get(pos);
        return posList.get(pos + 1) - posList.get(pos);
    }

    public static void main(String[] args) {
        _leetcode_6_4 obj = new _leetcode_6_4();
        System.out.println(obj.lastSubstring("zrziy"));
    }

}
