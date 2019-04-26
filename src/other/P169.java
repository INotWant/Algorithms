package other;

public class P169 {

    /*
    public int majorityElement(int[] nums) {
        int count = nums.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer integer = map.get(num);
            if (integer == null)
                integer = 0;
            if (++integer > count)
                return num;
            map.put(num, integer);
        }
        return -1;
    }
    // */

    public int majorityElement(int[] nums) {
        int count = 0;
        int num = nums[0];
        for (int n : nums) {
            if (num == n)
                count++;
            else {
                count--;
                if (count == 0) {
                    num = n;
                    count++;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        P169 p169 = new P169();
        int[] array = {1};
        System.out.println(p169.majorityElement(array));
    }

}
