package other;

public class P149 {

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    /*
    // 方法一
    public int maxPoints(Point[] points) {
        if (null == points || 0 == points.length) {
            return 0;
        }
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point pa = points[i];
            Set<Integer> set = new HashSet<>();
            set.add(i);
            list.add(set);
            for (int j = i + 1; j < points.length; j++) {
                Point pb = points[j];
                if (pa.x == pb.x && pa.y == pb.y) {
                    list.get(i).add(j);
                }
            }
        }
        int result = 1;
        Map<String, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            Point pa = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point pb = points[j];
                String key;
                if (pa.x == pb.x) {
                    key = pa.x + "=";
                } else if (pa.y == pb.y) {
                    key = "=" + pa.y;
                } else {
                    BigDecimal k = BigDecimal.valueOf(pa.y - pb.y).divide(BigDecimal.valueOf(pa.x - pb.x), 20, BigDecimal.ROUND_HALF_UP);
                    BigDecimal b = BigDecimal.valueOf(pa.y).subtract(k.multiply(BigDecimal.valueOf(pa.x)));
                    key = k + "," + b;
                }
                Set<Integer> set = map.get(key);
                if (null == set) {
                    set = new HashSet<>(list.get(i));
                    map.put(key, set);
                }
                set.add(j);
                if (set.size() > result) {
                    result = set.size();
                }
            }
        }
        return result;
    }
    */

    // 方法二
    public int maxPoints(Point[] points) {
        if (points.length == 0) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        int max = 2;
        for (int i = 0; i < points.length; i++) {
            int samePosition = 0;   // 重复位置的点 个数
            int sameSlope = 1;      // 斜率相同的点 个数
            for (int j = i + 1; j < points.length; j++) {
                // 判断是否为重复位置的点
                long x1 = points[j].x - points[i].x;
                long y1 = points[j].y - points[i].y;
                if (x1 == 0 && y1 == 0) {
                    samePosition++;
                } else {
                    sameSlope++;    // 第二个点，所以可以直接++
                    for (int k = j + 1; k < points.length; k++) {
                        long x2 = points[k].x - points[i].x;
                        long y2 = points[k].y - points[i].y;

                        if (x1 * y2 == x2 * y1) {
                            sameSlope++;
                        }
                    }
                }
                if (max < (samePosition + sameSlope)) {
                    max = samePosition + sameSlope;
                }
                sameSlope = 1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(94911151, 94911150);
        Point p3 = new Point(94911152, 94911151);

        Point[] points = {p1, p2, p3};

        P149 p149 = new P149();
        System.out.println(p149.maxPoints(points));
    }

}
