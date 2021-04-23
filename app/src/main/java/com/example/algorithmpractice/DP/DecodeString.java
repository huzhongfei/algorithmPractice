package com.example.algorithmpractice.DP;

import java.util.Arrays;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/21
 * desc:
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数
 *
 * 链接：https://leetcode-cn.com/problems/decode-ways
 */
class DecodeString {

    /**
     * 这其实是移到字符串的动态规划题，不难发现对于字符串 s 的某个位置 i 而言，我们只关心位置 i 自己能否形成独立 item
     * 和 位置 i 能够与 上一位置(i-1) 能否形成item,而不用关心 i-1 之前的位置。
     * 存在三种情况：
     *  1.只能由位置 i 的单独作为一个item， 设为a，转移的前提是 a 的数值范围为 [1, 9],转移的逻辑为 f[i] = f[i-1]。
     *  2.只能由位置 i 的与前一位置 (i-1) 共同作为 一个 item, 设为b， 转移的前提是 b 的数值范围为 [10, 26],转移逻辑为 f[i] = f[i -2];
     *  3.位置 i 既能作为独立 item 也能与上一位置形成item, 转移逻辑为 f[i] = f[i-1] + f[i-2]。
     *  因此，转移方程如下：
     *      { f[i] = f[i-1]; 1<= a <= 9;
     *        f[i] = f[i-2]; 10<= b <= 26;
     *        f[i] = f[i-1] + f[i-2]; 1<= a <=9, 10<= b <= 26
     *       -----------------------------------
     *
     * @param s
     * @return
     */

    public static int numDecodings(String s) {
//        int n = s.length();
//        s = " " + s;
//        char[] cs = s.toCharArray();
//        int[] f = new int[n + 1];
//        f[0] = 1;
//        for (int i = 1; i <= n; i++) {
//            // a : 代表「当前位置」单独形成 item
//            // b : 代表「当前位置」与「前一位置」共同形成 item
//            int a = cs[i] - '0', b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');
//            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
//            if (1 <= a && a <= 9) f[i] = f[i - 1];
//            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
//            if (10 <= b && b <= 26) f[i] += f[i - 2];
//        }
//        return f[n];


        int n = s.length();
        int[] f = new int[n+1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            if(s.charAt(i-1) !='0') {
                f[i] += f[i -1];
            }
            if(i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i -2) - '0') * 10 + (s.charAt(i -1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }

        System.out.println("f[n]: " + Arrays.toString(f));
        return f[n];
    }

    public static void main(String[] args) {
        String s = new String("12");
        System.out.println("result: " +  numDecodings(s));
    }
}
