package other;

import java.util.ArrayList;
import java.util.List;

public class _leetcode_9_3 {

    public static int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        double angleRadians = angle / 180. * Math.PI;

        int zeroCount = 0;
        int start = location.get(0);
        int end = location.get(1);
        List<Double> angles = new ArrayList<>();
        for (List<Integer> point : points) {
            long x = point.get(0) - start;
            long y = point.get(1) - end;
            if (x == 0 && y == 0)
                ++zeroCount;
            else
                angles.add(Math.atan2(y, x));
        }

        angles.sort(Double::compareTo);

        int size = angles.size();
        for (int i = 0; i < size; i++)
            angles.add(angles.get(i) + Math.PI * 2);

        int i = 0, j = i + 1;
        int max = 1;
        while (i < angles.size() && angles.size() - i > max) {
            while (j < angles.size()
                    && (Math.abs(angles.get(j) - angles.get(i) - angleRadians) < 1e-6 || angles.get(j) - angles.get(i) < angleRadians))
                ++j;
            if (j - i > max)
                max = j - i;
            ++i;
        }
        return max + zeroCount;
    }

    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();

//        List<Integer> p1 = new ArrayList<>();
//        p1.add(1);
//        p1.add(1);
//
//        List<Integer> p2 = new ArrayList<>();
//        p2.add(2);
//        p2.add(2);
//
//        List<Integer> p3 = new ArrayList<>();
//        p3.add(3);
//        p3.add(3);
//
//        List<Integer> p4 = new ArrayList<>();
//        p4.add(4);
//        p4.add(4);
//
//        List<Integer> p5 = new ArrayList<>();
//        p5.add(1);
//        p5.add(2);
//
//        List<Integer> p6 = new ArrayList<>();
//        p6.add(2);
//        p6.add(1);
//
//        points.add(p1);
//        points.add(p2);
//        points.add(p3);
//        points.add(p4);
//        points.add(p5);
//        points.add(p6);

        List<Integer> p1 = new ArrayList<>();
        p1.add(0);
        p1.add(0);

        List<Integer> p2 = new ArrayList<>();
        p2.add(0);
        p2.add(2);

//        List<Integer> p3 = new ArrayList<>();
//        p3.add(3);
//        p3.add(3);

        points.add(p1);
        points.add(p2);
//        points.add(p3);

        int angle = 90;
        List<Integer> location = new ArrayList<>();
        location.add(1);
        location.add(1);

        System.out.println(visiblePoints(points, angle, location));
    }

}
