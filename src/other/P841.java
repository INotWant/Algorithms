package other;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P841 {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> waitSet = new HashSet<>();
        Set<Integer> hasSet = new HashSet<>();

        hasSet.add(0);
        waitSet.addAll(rooms.get(0));
        while (waitSet.size() > 0) {
            Set<Integer> tmpSet = new HashSet<>(waitSet);
            hasSet.addAll(waitSet);
            waitSet.clear();
            for (int n1 : tmpSet) {
                for (int n2 : rooms.get(n1)) {
                    if (!hasSet.contains(n2)) {
                        waitSet.add(n2);
                    }
                }
            }
        }
        return hasSet.size() == rooms.size();
    }

}
