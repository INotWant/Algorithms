package other;

public class P201 {

    //*
    public int rangeBitwiseAnd(int m, int n) {
        int num = 0x80000000;
        for (int i = 1; i <= 30; i++) {
            num = num >> 1;
            if ((num & m) != (num & n))
                return n & (num << 1);
        }
        return m;
    }
    /*/

    // Error
    // 虽然 AC，但是自己发现了错误
    /*
    public int rangeBitwiseAnd(int m, int n) {
        int result = 0xffffffff;
        for (int i = m; i <= n; ) {
            if (result == 0)
                break;
            result &= i;
            if ((result & 0x0fffffff) == 0)
                i += 0x10000000;
            else if ((result & 0x00ffffff) == 0)
                i += 0x01000000;
            else if ((result & 0x000fffff) == 0)
                i += 0x00100000;
            else if ((result & 0x0000ffff) == 0)
                i += 0x00010000;
            else if ((result & 0x00000fff) == 0)
                i += 0x00001000;
            else if ((result & 0x000000ff) == 0)
                i += 0x00000100;
            else
                ++i;
            if (i < 0)
                break;
        }
        return result;
    }
    // */

    public static void main(String[] args) {
        P201 p201 = new P201();
        System.out.println(p201.rangeBitwiseAnd(257, 512));
    }

}
