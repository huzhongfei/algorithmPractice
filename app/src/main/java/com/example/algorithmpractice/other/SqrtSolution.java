package com.example.algorithmpractice.other;



/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/16
 * desc: x 的平方根  (x>=0)
 */
public class SqrtSolution {
    public static int mySqrt(int x) {
        // 二分查找
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(SqrtSolution.mySqrt(6));

    }
}
