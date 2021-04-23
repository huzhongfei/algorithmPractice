package com.example.algorithmpractice.other;

import java.util.ArrayList;
import java.util.List;

/**
 * author hufei
 * email: hu7fei2@sina.com
 * time: 2021/4/22
 * desc:格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 *
 * 格雷编码序列必须以 0 开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gray-code
 *
 * 设 n 阶 格雷码集合为 G(n), 则 G（n+1）阶格雷码为:
 *  1.给G(n) 阶格雷码每个元素二进制形式前面添加 0, 得到 G'(n);
 *  2.设 G(n)集合倒序（镜像）为R(n), 给 R(n)每个元素二进制形式前面添加 1，得到 R'(n);
 *  3.G(n+1) = G'(n) ∪ R'(n) 拼接两个集合即可得到下一阶格雷码。
 * 根据以上规律，可从0 阶格雷码推导致任何阶格雷码。
 * 代码解析：
 *  1.由于最高位前默认为0, 因此 G'(n) = G(n),只需在 res (即 G(n) ) 后添加 R'(n)即可。
 *  2.计算 R'(n):执行 heas =1 << i 计算出对应位数，以给 R(n)前添加 1得到对应 R'(n);
 *  3.倒序遍历 res(即G(n)): 依次求得 R'(n) 各元素添加至 res 尾端， 遍历完成后 res(即 G(n+1)).
 */
class GreeCode {

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>() {{ add(0); }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--)
                res.add(head + res.get(j));
            head <<= 1;
        }
        return res;

    }

    public static void main(String[] args) {
        GreeCode code = new GreeCode();
        code.grayCode(10);
    }
}
