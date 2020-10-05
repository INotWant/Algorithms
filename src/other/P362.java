package other;

public class P362 {

//    public boolean isPowerOfThree(int n) {
//        if (n == 0) {
//            return false;
//        }
//        if (n == 1) {
//            return true;
//        }
//        if (n % 3 != 0) {
//            return false;
//        } else {
//            return isPowerOfThree(n / 3);
//        }
//    }

    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

}
