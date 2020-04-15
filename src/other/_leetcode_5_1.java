package other;

public class _leetcode_5_1 {

    private static final int[] MORTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int dayOfYear(String date) {
        String[] split = date.split("-");

        int y = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int d = Integer.parseInt(split[2]);

        boolean isRunNian;
        isRunNian = y % 400 == 0 || (y % 4 == 0 && y % 100 != 0);

        int result = 0;
        for (int i = 0; i < m - 1; i++)
            result += MORTH_DAYS[i];
        if (m > 2 && isRunNian)
            ++result;
        return result + d;
    }

}
