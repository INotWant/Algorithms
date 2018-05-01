package other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class P56 {

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

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0)
            return result;
        if (intervals.size() == 1)
            return intervals;
        intervals.sort(Comparator.comparingInt(o -> o.start));
        int lStart = intervals.get(0).start;
        int lEnd = intervals.get(0).end;
        int lMaxEnd = lEnd;
        for (int i = 1; i < intervals.size(); i++) {
            lEnd = intervals.get(i - 1).end;
            if (lMaxEnd < lEnd)
                lMaxEnd = lEnd;
            int cStart = intervals.get(i).start;
            if (cStart > lMaxEnd) {
                result.add(new Interval(lStart, lMaxEnd));
                lStart = cStart;
            }
            if (i == intervals.size() - 1)
                result.add(new Interval(lStart, intervals.get(i).end > lMaxEnd ? intervals.get(i).end : lMaxEnd));
        }
        return result;
    }

}
