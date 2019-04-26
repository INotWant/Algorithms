package other;

public class _leetcode_1_2 {


    private final int[] array = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    public boolean confusingNumber(int N) {
        String str = String.valueOf(N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (array[str.charAt(i) - '0'] == -1)
                return false;
            sb.append(array[str.charAt(i) - '0']);
        }
        return !sb.reverse().toString().equals(str);
    }

    public static void main(String[] args) {
        _leetcode_1_2 obj = new _leetcode_1_2();
        System.out.println(obj.confusingNumber(6));
        System.out.println(obj.confusingNumber(89));
        System.out.println(obj.confusingNumber(11));
        System.out.println(obj.confusingNumber(25));
        System.out.println(obj.confusingNumber(4));
    }

}
