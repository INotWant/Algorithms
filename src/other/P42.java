package other;


/**
 * url :: https://leetcode.com/submissions/detail/144224084/
 * O(n) --> 使用数目保存第 i-n 的最大值！！！
 *
 * @author kissx on 2018/3/9.
 */
public class P42 {

    public int trap(int[] height) {
        // Error (考虑不全)
        /*
        if (height == null || height.length == 0)
            return 0;
        int[] newArray = new int[height.length + 1];
        System.arraycopy(height, 0, newArray, 0, height.length);
        newArray[height.length] = 0;
        int result = 0;
        int cMax = newArray[0];
        int count = 0;
        int cResult = 0;
        short state = 1;
        for (int i = 1; i < newArray.length; i++) {
            if (cMax <= newArray[i]) {
                cMax = newArray[i];
                result += cResult;
                cResult = 0;
                state = 1;
                count = 0;
            } else {
                ++count;
                if (state == 2 && newArray[i] > newArray[i + 1]) {
                    int temp = 0;
                    for (int j = i - 1; j > i - count; j--)
                        temp += cMax - (newArray[j] > newArray[i] ? newArray[j] : newArray[i]);
                    result += (cResult - temp);
                    cMax = newArray[i];
                    cResult = 0;
                    count = 0;
                    state = 1;
                }
                if (i + 1 < newArray.length && newArray[i] < newArray[i + 1])
                    state = 2;
                cResult += (cMax - newArray[i]);
            }
        }
        return result;
        */
        if (height == null || height.length == 0)
            return 0;
        int[] array = new int[height.length];
        array[array.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            if (height[i] > array[i + 1])
                array[i] = height[i];
            else
                array[i] = array[i + 1];
        }
        int result = 0;
        int cMax = height[0];
        for (int i = 1; i < height.length; i++) {
            if (cMax > height[i]) {
                int temp = array[i] > cMax ? cMax : array[i];
                result += (temp - height[i]);
            } else
                cMax = height[i];
        }
        return result;
    }


    public static void main(String[] args) {
        P42 p42 = new P42();
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {2, 1, 0, 2};
        int[] height = {5, 4, 1, 2};
//        int[] height = {5, 2, 1, 2, 1, 5};
        System.out.println(p42.trap(height));
    }
}
