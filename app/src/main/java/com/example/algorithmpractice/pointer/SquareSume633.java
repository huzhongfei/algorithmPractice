package com.example.algorithmpractice.pointer;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/28
 * desc:给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a² + b² = c 。
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers/solution/ping-fang-shu-zhi-he-by-leetcode-solutio-8ydl/
 */
public class SquareSume633 {

    /**
     *  双指针
     *  不失一般性，可以假设 a ≤ b。 初始时 a = 0, b = 根号c. 进行如下操作：
     *      1.如果 a² + b² = c, 我们找到题名的要求的一个解，返回true；
     *      2.如果 a² + b² < c, 此时我们要将 a 的值加1，继续查找；
     *      3.如果 a² + b² > c，此时需要将 b 的值减1，继续查找。
     *   当  a = b 时，结束查找，此时如果仍然没有找到整数 a 和 b 满足 a² + b² = c， 则说明不存在题目要求的解，返回false.
     * @param c
     * @return
     */
    public boolean judgeSquareSum2(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while(left <= right) {
            int sum = left*left + right*right;
            if(sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }

        return false;
    }

    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }


}
