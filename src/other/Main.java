package other;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        int countTotal = (int) in.nval;
        int count = 0;
        while (count < countTotal) {
            double[][] arr = new double[4][2];
            double dis = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    in.nextToken();
                    arr[i][j] = in.nval;
                    dis += Math.abs(arr[i][j] - 0.5);
                }
            }
            double[] sArr = new double[4];
            Arrays.fill(sArr, -1);
            for (int i = 0; i < 4; i++) {
                double x = arr[i][0];
                double y = arr[i][1];
                if (x == 0 && sArr[0] == -1)
                    sArr[0] = y;
                else if (y == 0 && sArr[1] == -1)
                    sArr[1] = x;
                else if (x == 1 && sArr[2] == -1)
                    sArr[2] = y;
                else
                    sArr[3] = x;
            }
            double currDis1;
            double currDis2;
            currDis1 = sArr[0] + sArr[1] + Math.abs(1 - sArr[2]) + Math.abs(1 - sArr[3]);
            currDis2 = sArr[2] + sArr[3] + Math.abs(1 - sArr[0]) + Math.abs(1 - sArr[1]);
            currDis1 = Math.min(currDis1, currDis2);
            dis = Math.min(dis, currDis1);
            // 1
            int p = 0;
            double[] solve = getSolve(sArr[p] * sArr[p] - sArr[p]);
            if (solve != null) {
                currDis1 = Math.abs(sArr[1] - solve[0]) + Math.abs(sArr[2] - (1 - sArr[0])) + Math.abs(sArr[3] - (1 - solve[0]));
                currDis2 = Math.abs(sArr[1] - solve[1]) + Math.abs(sArr[2] - (1 - sArr[0])) + Math.abs(sArr[3] - (1 - solve[1]));
                currDis1 = Math.min(currDis1, currDis2);
                dis = Math.min(dis, currDis1);
            }
            // 2
            p = 1;
            solve = getSolve(sArr[p] * sArr[p] - sArr[p]);
            if (solve != null) {
                currDis1 = Math.abs(sArr[0] - solve[0]) + Math.abs(sArr[2] - (1 - solve[0])) + Math.abs(sArr[3] - (1 - sArr[1]));
                currDis2 = Math.abs(sArr[0] - solve[1]) + Math.abs(sArr[2] - (1 - solve[1])) + Math.abs(sArr[3] - (1 - sArr[1]));
                currDis1 = Math.min(currDis1, currDis2);
                dis = Math.min(dis, currDis1);
            }
            // 3
            p = 2;
            solve = getSolve(sArr[p] * sArr[p] - sArr[p]);
            if (solve != null) {
                currDis1 = Math.abs(sArr[0] - (1 - sArr[2])) + Math.abs(sArr[1] - (1 - solve[0])) + Math.abs(sArr[3] - solve[0]);
                currDis2 = Math.abs(sArr[0] - (1 - sArr[2])) + Math.abs(sArr[1] - (1 - solve[1])) + Math.abs(sArr[3] - solve[1]);
                currDis1 = Math.min(currDis1, currDis2);
                dis = Math.min(dis, currDis1);
            }
            // 4
            p = 3;
            solve = getSolve(sArr[p] * sArr[p] - sArr[p]);
            if (solve != null) {
                currDis1 = Math.abs(sArr[0] - (1 - solve[0])) + Math.abs(sArr[1] - (1 - sArr[3])) + Math.abs(sArr[2] - solve[0]);
                currDis2 = Math.abs(sArr[0] - (1 - solve[1])) + Math.abs(sArr[1] - (1 - sArr[3])) + Math.abs(sArr[2] - solve[1]);
                currDis1 = Math.min(currDis1, currDis2);
                dis = Math.min(dis, currDis1);
            }
            out.println(String.format("%.12f", dis));
            ++count;
        }
        out.flush();
    }

    private static double[] getSolve(double c) {
        double pan = 1 + 4 * c;
        if (pan < 0)
            return null;
        double[] result = new double[2];
        pan = Math.sqrt(pan);
        result[0] = (1 + pan) / 2;
        result[1] = (1 - pan) / 2;
        return result;
    }

}

/*
import java.io.*;
import java.math.BigInteger;

public class Main {

    private final static int VALUE = 1000000007;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        int countTotal = (int) in.nval;
        int count = 0;
        while (count < countTotal) {
            in.nextToken();
            long n = (long) in.nval;
            in.nextToken();
            long a = (long) in.nval;
            in.nextToken();
            long b = (long) in.nval;

            long result;
            if (n == 1)
                result = b;
            else {
                BigInteger bn = new BigInteger("" + (n * 2));
                BigInteger bcurr = new BigInteger("" + 2);
                BigInteger blastMin = new BigInteger("" + b);

                BigInteger b9 = new BigInteger(String.valueOf(9));
                BigInteger b7 = new BigInteger(String.valueOf(7));
                BigInteger b2 = new BigInteger(String.valueOf(2));
                BigInteger ba = new BigInteger(String.valueOf(a));
                BigInteger bb = new BigInteger(String.valueOf(b));

                while (bn.compareTo(bcurr) != 0) {
                    BigInteger mc = bcurr.multiply(bcurr).multiply(b9).divide(b2).multiply(ba);
                    BigInteger oc = blastMin.multiply(b7);
                    BigInteger sc = mc.add(oc);
                    BigInteger zc = bcurr.multiply(bcurr).multiply(bcurr).multiply(ba.add(bb)).subtract(bcurr.multiply(bcurr).multiply(ba));
                    if (sc.compareTo(zc) > 0)
                        blastMin = zc;
                    else
                        blastMin = sc;
                    bcurr = bcurr.multiply(new BigInteger(String.valueOf(2)));
                }
                BigInteger bresult = blastMin.mod(new BigInteger(String.valueOf(VALUE)));
                result = bresult.intValue();
            }
            out.println((result % VALUE));
            ++count;
        }
        out.flush();
    }
}*/

/*



    private final static int VALUE = (int) (10e9 + 7);

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        int countTotal = (int) in.nval;
        int count = 0;
        while (count < countTotal) {
            in.nextToken();
            long n = (long) in.nval;
            in.nextToken();
            long a = (long) in.nval;
            in.nextToken();
            long b = (long) in.nval;

            long result;
            if (n == 1)
                result = b;
            else {
                long curr = 2;
                long lastMin = b;
                while (curr != n) {
                    long mc = (9 * curr * curr / 2) * a;
                    long oc = 7 * lastMin;
                    long sc = mc + oc;
                    long zc = curr * curr * curr * (a + b) - curr * curr * a;
                    lastMin = Math.min(zc, sc);
                    curr *= 2;
                }
                long zc = curr * curr * curr * (a + b) - curr * curr * a;
                long sc = (9 * curr * curr / 2) * a + 7 * lastMin;
                result = Math.min(zc, sc);
            }
            out.println((result % VALUE));
            ++count;
        }
        out.flush();
    }
// */