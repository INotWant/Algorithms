package other;

import java.util.*;

public class P241 {

    public static List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.length() == 0)
            return new LinkedList<>();
        return helper(0, input.length() - 1, input, new HashMap<>());
    }

    private static List<Integer> helper(int start, int end, String input, Map<String, List<Integer>> saveMap) {
        List<Integer> result = new ArrayList<>();

        boolean flag = true;
        for (int i = start; i <= end; i++)
            if (!Character.isDigit(input.charAt(i))) {
                flag = false;
                break;
            }

        if (flag) {
            result.add(Integer.parseInt(input.substring(start, end + 1)));
            return result;
        }

        String key = start + "," + end;
        if (saveMap.get(key) != null)
            return saveMap.get(key);

        for (int i = start + 1; i < end; i++) {
            if (Character.isDigit(input.charAt(i)))
                continue;
            List<Integer> r1 = helper(start, i - 1, input, saveMap);
            List<Integer> r2 = helper(i + 1, end, input, saveMap);
            switch (input.charAt(i)) {
                case '+':
                    for (int n1 : r1)
                        for (int n2 : r2)
                            result.add(n1 + n2);
                    break;
                case '-':
                    for (int n1 : r1)
                        for (int n2 : r2)
                            result.add(n1 - n2);
                    break;
                case '*':
                    for (int n1 : r1)
                        for (int n2 : r2)
                            result.add(n1 * n2);
                    break;
            }
        }
        saveMap.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        String str = "2-1-1";
        System.out.println(diffWaysToCompute(str));
    }

}
