package other;

import java.util.*;

/**
 * @author kissx on 2018/3/16.
 */
public class _MI_P1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int count = 0;
        while (count < n) {
            String line = scanner.nextLine();
            List<Integer> list = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == 'Z') {
                    list.add(2);
                    sb.append("ZERO");
                } else if (c == 'W') {
                    list.add(4);
                    sb.append("TWO");
                } else if (c == 'X') {
                    list.add(8);
                    sb.append("SIX");
                } else if (c == 'G') {
                    list.add(0);
                    sb.append("EIGHT");
                } else if (c == 'U') {
                    list.add(6);
                    sb.append("FOUR");
                }
            }
            String temp = sb.toString();
            for (int i = 0; i < temp.length(); i++) {
                char c = temp.charAt(i);
                line = line.replaceFirst(String.valueOf(c), "");
            }
            sb.delete(0, sb.length());
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == 'T') {
                    list.add(5);
                    sb.append("THREE");
                } else if (c == 'F') {
                    list.add(7);
                    sb.append("FIVE");
                } else if (c == 'S') {
                    list.add(9);
                    sb.append("SEVEN");
                } else if (c == 'O') {
                    list.add(3);
                    sb.append("ONE");
                }
            }
            temp = sb.toString();
            for (int i = 0; i < temp.length(); i++) {
                char c = temp.charAt(i);
                line = line.replaceFirst(String.valueOf(c), "");
            }
            int num = line.length() / 4;
            for (int i = 0; i < num; i++)
                list.add(1);
            Collections.sort(list);
            for (Integer str : list)
                System.out.printf(String.valueOf(str));
            System.out.println();
            ++count;
        }
    }

}
