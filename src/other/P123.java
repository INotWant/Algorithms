package other;

import java.util.ArrayList;
import java.util.List;

public class P123 {

    // AC 但不是好方法
    /*
    public int maxProfit(int[] prices) {
        if (null == prices || 0 == prices.length || 1 == prices.length)
            return 0;
        int[] array = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            array[i - 1] = diff;
        }
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int value : array) {
            if (value > 0) {
                list.add(sum);
                sum = 0;
                list.add(value);
            } else
                sum += value;
        }
        if (list.size() > 0 && list.get(0) <= 0)
            list.remove(0);
        int startIndex, endIndex, count = 0;
        int max = 0, last = 0;
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            last += list.get(i);
            if (last <= 0) {
                last = 0;
                count = 0;
            } else {
                ++count;
                if (last > max) {
                    max = last;
                    startIndex = i - count + 1;
                    endIndex = i;
                    result = Math.max(result, max + helper(list, startIndex, endIndex));
                }
            }
        }
        return result;
    }

    private int helper(List<Integer> list, int startIndex, int endIndex) {
        int max = 0;
        int last = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == startIndex) {
                i = endIndex;
                last = 0;
                continue;
            }
            last += list.get(i);
            if (last < 0) {
                last = 0;
            } else {
                if (last > max) {
                    max = last;
                }
            }
        }
        return max;
    }
    // */

    public int maxProfit(int[] prices) {
        /*
         *  对于任意一天考虑四个变量:
         *      fstBuy: 在该天第一次买入股票可获得的最大收益
         *      fstSell: 在该天第一次卖出股票可获得的最大收益
         *      secBuy: 在该天第二次买入股票可获得的最大收益
         *      secSell: 在该天第二次卖出股票可获得的最大收益
         *      分别对四个变量进行相应的更新, 最后secSell就是最大
         *      收益值(secSell >= fstSell)
         */
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for (int p : prices) {
            fstBuy = Math.max(fstBuy, -p);
            fstSell = Math.max(fstSell, fstBuy + p);
            secBuy = Math.max(secBuy, fstSell - p);
            secSell = Math.max(secSell, secBuy + p);
        }
        return secSell;
    }

    public static void main(String[] args) {
//        int[] array = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        int[] array = {7, 6, 4, 3, 1};
        P123 p123 = new P123();
        System.out.println(p123.maxProfit(array));
    }

}
