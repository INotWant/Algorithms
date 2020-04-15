package other;

public class P1217 {

    public int minCostToMoveChips(int[] chips) {
        int ji = 0;
        int ou = 0;
        for (int chip : chips) {
            if (chip % 2 == 0)
                ++ou;
            else
                ++ji;
        }
        return Math.min(ji, ou);
    }

}
