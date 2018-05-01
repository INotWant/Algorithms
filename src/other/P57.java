package other;

import java.util.ArrayList;
import java.util.List;

public class P57 {

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int start = newInterval.start;
        int end = newInterval.end;
        int i = 0;
        while (true) {
            if (i == intervals.size()) {
                result.add(new Interval(start, end));
                break;
            }
            if (start > intervals.get(i).end) {
                result.add(intervals.get(i));
                ++i;
            } else if (intervals.get(i).start > end) {
                result.add(new Interval(start, end));
                result.add(intervals.get(i));
                ++i;
                break;
            } else {
                start = Math.min(intervals.get(i).start, start);
                end = Math.max(intervals.get(i).end, end);
                ++i;
                if (end == intervals.get(i - 1).end) {
                    result.add(new Interval(start, end));
                    break;
                }
            }
        }
        for (; i < intervals.size(); ++i)
            result.add(intervals.get(i));
        return result;
    }

}
