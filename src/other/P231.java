package other;

/**
 * @author iwant
 * @date 19-6-25 19:30
 * @desc
 */
public class P231 {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if (((n >> i) & 0x01) == 1)
                if (++count > 1)
                    return false;
        }
        return count == 1;
    }

}
