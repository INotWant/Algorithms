package other;

public class P258 {

    public int addDigits(int num) {
        if (num < 10)
            return num;
        else {
            int sum = 0;
            do {
                sum += (num % 10);
                num /= 10;
            } while (num > 0);
            return addDigits(sum);
        }
    }

}
