package other;

public class P481 {

    public int magicalString(int n) {
        if (n <= 0)
            return 0;
        if (n <= 3)
            return 1;
        String str = build(n);
        int res = 0;
        for (int i = 0; i < n; i++)
            if (str.charAt(i) == 1)
                ++res;

        return res;
    }


    private String build(int n) {
        char[] chars1 = new char[n];
        char[] chars2 = new char[n];

        chars1[0] = 1;
        chars1[1] = 2;
        chars2[0] = 1;

        int i = 1, j = 1;
        int end1 = 2, end2 = 1;
        int last;
        while (end1 < n && end2 < n) {
            while (i < end1 && end2 < n) {
                if (chars1[i] == 1) {
                    last = chars2[end2 - 1];
                    chars2[end2++] = last == 1 ? (char) 2 : (char) 1;
                } else if (chars1[i] == 2) {
                    last = chars2[end2 - 1];
                    chars2[end2++] = last == 1 ? (char) 2 : (char) 1;
                    if (end2 < n)
                        chars2[end2++] = last == 1 ? (char) 2 : (char) 1;
                }
                ++i;
            }
            if (j == 1) {
                chars1[end1++] = 2;
                ++j;
            }
            while (j < end2 && end1 < n && end2 < n) {
                if (chars2[j] == 1) {
                    last = chars1[end1 - 1];
                    chars1[end1++] = last == 1 ? (char) 2 : (char) 1;
                } else if (chars2[j] == 2) {
                    last = chars1[end1 - 1];
                    chars1[end1++] = last == 1 ? (char) 2 : (char) 1;
                    if (end1 < n)
                        chars1[end1++] = last == 1 ? (char) 2 : (char) 1;
                }
                ++j;
            }
        }

        if (end1 == n)
            return new String(chars1);
        else
            return new String(chars2);
    }

    public static void main(String[] args) {
        P481 p481 = new P481();
        System.out.println(p481.magicalString(1000));

//        int index = 2;
//        int[] arr = {1, 4, 2, 5};
//        // 以下两个的结果不同
//        arr[index++] = arr[index - 1];  // 左值先运算了，也就 index 先 +1 了
//        arr[index] = arr[index++ - 1];
//        System.out.println(Arrays.toString(arr));
    }

}
