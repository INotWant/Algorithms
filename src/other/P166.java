package other;

import java.util.HashMap;
import java.util.Map;

public class P166 {

    public String fractionToDecimal(int numerator, int denominator) {
        int sign = 1;
        if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0))
            sign = -1;
        StringBuilder sb = new StringBuilder();
        long newNumerator = numerator;
        long newDenominator = denominator;
        if (Math.abs(newNumerator) >= Math.abs(newDenominator)) {
            sb.append(Math.abs(newNumerator) / Math.abs(newDenominator));
            newNumerator = newNumerator % newDenominator;
        } else
            sb.append(0);
        newNumerator = newNumerator < 0 ? -newNumerator : newNumerator;
        newDenominator = newDenominator < 0 ? -newDenominator : newDenominator;
        String preResult = sb.toString();
        sb.delete(0, sb.length());
        int pos = 0;
        Map<Long, Integer> map = new HashMap<>();
        while (newNumerator > 0) {
            map.put(newNumerator, pos);
            newNumerator *= 10;
            if (newNumerator < newDenominator)
                sb.append(0);
            else {
                long d = newNumerator / newDenominator;
                newNumerator %= newDenominator;
                if (map.get(newNumerator) != null) {
                    sb.insert(map.get(newNumerator), "(");
                    sb.append(d).append(')');
                    break;
                } else
                    sb.append(d);
            }
            ++pos;
        }
        String result = sb.length() == 0 ? preResult : preResult + '.' + sb.toString();
        result = sign == -1 ? "-" + result : result;
        return result;
    }

    public static void main(String[] args) {
        P166 p166 = new P166();
        System.out.println(p166.fractionToDecimal(1, 2));
        System.out.println(p166.fractionToDecimal(2, 1));
        System.out.println(p166.fractionToDecimal(10, 2));
        System.out.println(p166.fractionToDecimal(1, 3));
        System.out.println(p166.fractionToDecimal(1, 7));
        System.out.println(p166.fractionToDecimal(4, 333));
        System.out.println(p166.fractionToDecimal(0, 333));
        System.out.println(p166.fractionToDecimal(-50, 8));
        System.out.println(p166.fractionToDecimal(7, -12));
        System.out.println(p166.fractionToDecimal(50, -8));
        System.out.println(p166.fractionToDecimal(1, Integer.MIN_VALUE));
        System.out.println(p166.fractionToDecimal(-1, Integer.MIN_VALUE));
    }

}