package other;

import java.util.Arrays;

public class P274 {

    public static int hIndex(int[] citations) {
        if (citations.length == 0)
            return 0;
        if (citations.length == 1)
            return citations[0] > 0 ? 1 : 0;

        Arrays.sort(citations);
        int h = 0;
        int i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            ++h;
            --i;
        }
        return h;
    }

    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(hIndex(citations));
    }

}
