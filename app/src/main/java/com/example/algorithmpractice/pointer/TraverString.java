package com.example.algorithmpractice.pointer;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/28
 * desc:给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的
 *链接：https://leetcode-cn.com/problems/interleaving-string
 *
 *双指针行不通，
 */
public class TraverString {

    /**
     * 这个问题正确解法是 动态规划
     * 首先如果 |s1| + |s2| ≠ |s3|,那s3必然不可能由s1 和 s2 交错组成。
     * 在|s1| + |s2| = |s3|时， 我们可以动态规划来求解。
     * 定义 f(i,j) 表示s1 前 i 个元素和 s2 的前 j个元素是否能交错组成 s3 的前i+j 元素。
     * 如果 s1 的第 i 个元素和 s3 的 i+j 个元素相等，那么s1 的前i个元素和s2 的前j个元素是否能
     * 交错组成s3 的前 i+j 个元素取决于 f(i-1,j), 在此情况下如果 f(i-1,j)为真，则f(i,j)也为真。
     * 同样，如果s2的j个元素和 s3的第 i+j 个元素相等并且 f(i, j-1) 为真，则f(i,j)也为真。
     * 于是我们可以推导出这样的动态规划转移方程：
     *      f(i,j) = [f(i-1, j) and s1(i-1) = s3(p)] or [f(i, j-1) and s2(j-1) = s3(p)]
     * 其中 p = i+j -1。
     * 边界条件为 f(0,0) 为 true。
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if(n+m != t) {
            return false;
        }

        boolean[][] f = new boolean[n+1][m+1];
        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j -1;
                if(i > 0) {
                    f[i][j] = f[i][j] || (f[i-1][j] && s1.charAt(i-1) == s3.charAt(p));
                }
                if(j > 0) {
                    f[i][j] = f[i][j] || (f[i][j-1] && s2.charAt(j-1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];

    }

    /**
     * 滚动数组
     *
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if(m+n != t) {
            return false;
        }

        boolean[] f= new boolean[m+1];

        f[0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i+j -1;
                if (i>0) {
                    f[j] = f[j] && s1.charAt(i-1) == s3.charAt(p);
                }

                if(j > 0) {
                    f[j] = f[j] || (f[j -1] && s2.charAt(j-1) == s3.charAt(p));
                }
            }
        }

        return f[m];
    }
}
