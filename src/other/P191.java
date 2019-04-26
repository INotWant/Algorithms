package other;

/**
 * @author iwant
 * @date 2019-04-26 14:04
 */
public class P191 {

    public int hammingWeight(int n) {
        int sum = 0;
        for (int i = 1; i <= 32; i++)
            sum += (n >> i & 0x01) == 1 ? 1 : 0;
        return sum;
    }

}
