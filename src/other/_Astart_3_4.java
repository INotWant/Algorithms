package other;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class _Astart_3_4 {
    //*
    private static final int VALUE = 998244353;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        int M = (int) in.nval;
        in.nextToken();
        int Q = (int) in.nval;
        BitSet bitSet = new BitSet((int) Math.pow(2, M - 1));
        in.nextToken();
        String str;
        if (in.sval != null)
            str = in.sval;
        else
            str = String.valueOf(in.nval);
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == '1')
                bitSet.set(i + 1);
        Map<Integer, Integer> iMap = new HashMap<>();
        int q = 0;
        while (q < Q) {
            in.nextToken();
            int cmd = (int) in.nval;
            if (cmd == 1) {
                in.nextToken();
                int p = (int) in.nval;
                in.nextToken();
                int v = (int) in.nval;
                iMap.put(p, v);
            } else {
                in.nextToken();
                int x = (int) in.nval;
                int maxX = (int) Math.pow(2, M - 1);
                int minX = 1;
                int result = 1;
                int start = 1;
                int pos = 1;
                int next = 1;
                while (start <= M - 1) {
                    int lastPos = pos;
                    int mid = (minX + maxX) / 2;
                    if (x > mid) {
                        minX = mid + 1;
                        pos = 2 * pos + 1;
                        next = 2 * pos;
                    } else {
                        maxX = mid;
                        pos = 2 * pos;
                        next = 2 * pos + 1;
                    }
                    if (bitSet.get(lastPos))
                        result *= getSum(pos, iMap, bitSet, (int) Math.pow(2, M - 1));
                    ++start;
                }
                out.println(result);
            }
            ++q;
        }
        out.flush();
    }

    private static int getSum(int pos, Map<Integer, Integer> iMap, BitSet bitSet, int max) {
        if (pos * 2 >= max) {
            Integer a = iMap.get(2 * pos);
            Integer b = iMap.get(2 * pos + 1);
            if (a == null)
                a = 2 * pos - max + 1;
            if (b == null)
                b = 2 * pos - max + 2;
            if (bitSet.get(pos))
                return a * b;
            else
                return a + b;
        } else {
            if (bitSet.get(pos))
                return getSum(2 * pos, iMap, bitSet, max) * getSum(2 * pos + 1, iMap, bitSet, max);
            else
                return getSum(2 * pos, iMap, bitSet, max) + getSum(2 * pos + 1, iMap, bitSet, max);
        }
    }

    //*/
}