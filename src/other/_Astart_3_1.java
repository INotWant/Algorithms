package other;

import java.io.*;

public class _Astart_3_1 {

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        int count = (int) in.nval;
        int num = 0;
        int a;
        while (num < count) {
            in.nextToken();
            a = (int) in.nval;
            int result;
            if (a % 2 == 0)
                result = a + 1;
            else
                result = a - 1;
            out.println(result);
            ++num;
        }
        out.flush();
    }

}
