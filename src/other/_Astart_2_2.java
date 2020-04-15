package other;

import java.util.*;

public class _Astart_2_2 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int c = 0;
        List<String> results = new ArrayList<>();
        while (c < count) {
            boolean hasResult = true;
            int len = scanner.nextInt();
            int n = scanner.nextInt();
            List[] lists = new List[n];
            for (int i = 0; i < len; i++) {
                String a = scanner.next();
                String b = scanner.next();
                if (!hasResult) {
                    continue;
                }
                List[] tmp = new List[26];
                for (int j = 0; j < b.length(); j++) {
                    int pos = b.charAt(j) - 'a';
                    if (tmp[pos] == null)
                        tmp[pos] = new ArrayList();
                    tmp[pos].add(j + 1);
                }
                for (int j = 0; j < a.length(); j++) {
                    int pos = a.charAt(j) - 'a';
                    if (i == 0 && tmp[pos] != null) {
                        lists[j] = new ArrayList(tmp[pos]);
                    } else {
                        List list1 = tmp[pos];
                        List list2 = lists[j];
                        if (list1 == null || list2 == null || list1.size() == 0 || list2.size() == 0) {
                            results.add("-1");
                            hasResult = false;
                            break;
                        }
                        int k = 0, l = 0;
                        while (k < list1.size() && l < list2.size()) {
                            if ((int) list1.get(k) < (int) list2.get(l)) {
                                ++k;
                            } else if ((int) list1.get(k) > (int) list2.get(l)) {
                                list2.remove(l);
                            } else {
                                ++k;
                                ++l;
                            }
                        }
                        while (l < list2.size()) {
                            list2.remove(l);
                        }
                        if (list2.size() == 0) {
                            results.add("-1");
                            hasResult = false;
                        }
                    }
                }
            }
            if (hasResult) {
                print(lists, results);
            }
            c++;
        }
        for (String result : results) {
            System.out.println(result);
        }
    }

    private static void print(List[] lists, List<String> results) {
        boolean hasResult = true;
        Set<Integer> set = new LinkedHashSet<>();
        for (List list : lists) {
            boolean flag = false;
            for (Object o : list) {
                int pos = (int) o;
                if (!set.contains(pos)) {
                    set.add(pos);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                hasResult = false;
                break;
            }
        }
        if (hasResult) {
            StringBuilder sb = new StringBuilder();
            for (Integer pos : set) {
                sb.append(pos).append(" ");
            }
            sb.delete(sb.length() - 1, sb.length());
            results.add(sb.toString());
        } else {
            results.add("-1");
        }
    }
}
