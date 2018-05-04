package other;

import java.util.LinkedList;

public class P66 {

    public int[] plusOne(int[] digits) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        boolean is = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i];
            if (i == digits.length - 1) {
                linkedList.add((num + 1) % 10);
                is = ((num + 1) % 10 == 0);
            } else {
                if (is) {
                    num = (num + 1) % 10;
                    is = (num == 0);
                }
                linkedList.addFirst(num);
            }
        }
        if (is)
            linkedList.addFirst(1);
        int[] array = new int[linkedList.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = linkedList.get(i);
        return array;
    }

}
