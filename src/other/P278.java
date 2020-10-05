package other;

public class P278 {

    public int firstBadVersion(int n) {
        long l = 1;
        long r = n;

        long mid = (l + r) / 2;
        boolean flag;
        while (l < r) {
            flag = isBadVersion((int) mid);
            if (flag) {
                r = mid;
                mid = (l + r) / 2;
            } else {
                l = mid + 1;
                mid = (l + r) / 2;
            }
        }
        return (int) l;
    }

    private boolean isBadVersion(int n) {
        return true;
    }

}
