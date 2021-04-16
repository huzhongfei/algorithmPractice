package com.example.algorithmpractice.string;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/16
 * desc: 二进制求和
 * 给定两个二进制字符串，返回它们的和（用二进制表示).
 */
public class ByteStringSum {

    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0')); // 该位的值
            carry /= 2; // 进位
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();

        return ans.toString();



//        int aInt = Integer.parseInt(a, 2);
//        int bInt = Integer.parseInt(b, 2);
//        // 字符串长度超过33位，不能转化为Interger
//        return Integer.toBinaryString(aInt+bInt);
    }

    public static void main(String[] args) {
        ByteStringSum sum = new ByteStringSum();
        sum.addBinary("101", "10");
    }
}
