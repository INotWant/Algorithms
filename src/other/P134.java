package other;

public class P134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            int gasSum = gas[i];
            int count = 1;
            int p = i;
            while (count <= gas.length) {
                int newP = (p + 1) % gas.length;
                if (gasSum >= cost[p]) {
                    if (count == gas.length) {
                        return i;
                    }
                    gasSum -= cost[p];
                    gasSum += gas[newP];
                } else {
                    break;
                }
                p = newP;
                ++count;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        P134 p134 = new P134();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 2, 1};
        System.out.println(p134.canCompleteCircuit(gas, cost));
    }
}
